package com.lhs.weichat.core.handler;

import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.ClientLoginMessage;
import com.lhs.weichat.core.bean.ClientRequestMessage;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.PingMessage;
import com.lhs.weichat.core.bean.ServerLoginMessage;
import com.lhs.weichat.service.AttachmentService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MsgChatHandler
 *
 * @author longhuashen
 * @since 17/5/22
 */
@Sharable
@Component
public class MsgChatHandler extends SimpleChannelInboundHandler<Msg.Message> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private AttachmentService attachmentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgChatHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Message message) throws Exception {
System.out.println(">>>>>msgChat channelRead0:" + message);
        switch (message.getMessageType()) {
            case CLIENT_LOGIN:
                if (message.getClientLoginMessage() != null) {
                    ClientLoginMessage clientLoginMessage = new ClientLoginMessage();
                    clientLoginMessage.setToken(message.getClientLoginMessage().getToken());
                    clientLoginMessage.setUserId(message.getClientLoginMessage().getUserId());
                    ctx.fireChannelRead(clientLoginMessage);
                } else {
                    LOGGER.info("消息异常，用户登录消息为null。");
                }
                break;
            case CLIENT_PONG:
                if (message.getPingMessage() != null
                        && (message.getMessageType()
                        .equals(Msg.MessageType.CLIENT_PONG))) {
                    LOGGER.info("client pong消息不用处理");
                } else {
                    LOGGER.info("消息异常，client pong消息为null或者消息类型异常。");
                }
                break;
            case CLIENT_PING:
                if (message.getPingMessage() != null
                        && (message.getMessageType()
                        .equals(Msg.MessageType.CLIENT_PING))) {
                    PingMessage msg = new PingMessage();
                    msg.setClientId(message.getPingMessage().getClientId());
                    msg.setMessageType(Msg.MessageType.CLIENT_PING);
                    ctx.fireChannelRead(msg);
                } else {
                    LOGGER.info("消息异常，client ping消息为null或者消息类型异常。");
                }
                break;
            case SERVER_LOGIN:
                if (message.getServerLoginMessage() != null) {
                    ServerLoginMessage serverLoginMessage = new ServerLoginMessage();
                    serverLoginMessage.setIp(message.getServerLoginMessage().getIp());
                    serverLoginMessage.setPort(message.getServerLoginMessage().getPort());
                    ctx.fireChannelRead(message);
                }
                break;
            case SERVER_PING:
                if (message.getPingMessage() != null
                        && (message.getMessageType()
                        .equals(Msg.MessageType.SERVER_PING))) {
                    PingMessage pingMessage = new PingMessage();
                    pingMessage.setClientId(message.getPingMessage().getClientId());
                    pingMessage.setMessageType(Msg.MessageType.SERVER_PING);
                    ctx.fireChannelRead(message);
                } else {
                    LOGGER.info("消息异常，client ping消息为null或者消息类型异常。");
                }
                break;
            case SERVER_PONG:
                LOGGER.info("server pong消息不用处理");
                break;
            case CHAT_MESSAGE:
                if (message.getChatMessage() != null) {
                    ChatMessage msg = new ChatMessage();
                    msg.setContent(message.getChatMessage().getContent());
                    msg.setFromId(message.getChatMessage().getFromId());
                    msg.setToId(message.getChatMessage().getToId());
                    msg.setToken(message.getChatMessage().getToken());
                    msg.setTransfer(message.getChatMessage().getTransfer());
                    msg.setType(ChatMessage.TYPE_SEND);
                    msg.setMsgType(message.getChatMessage().getMsgType());
                    msg.setChatGroupId(message.getChatMessage().getChatGroupId());
                    msg.setDiscussionGroupId(message.getChatMessage()
                            .getDiscussionGroupId());
                    msg.setUuid(message.getChatMessage().getUuid());
                    msg.setStatus(ChatMessage.STATUS_SEND);
                    // 都以当前服务器时间为准
                    msg.setDate(new Date());
                    msg.setContentType(message.getChatMessage().getContentType());


                    ctx.fireChannelRead(msg);
                } else {
                    LOGGER.info("消息异常，chat message消息为null。");
                }
                break;
            case CLIENT_REQUEST:
                if (message.getClientRequestMessage() != null) {
                    ClientRequestMessage r = new ClientRequestMessage();
                    r.setParameter(message.getClientRequestMessage().getParameter());
                    r.setRequestType(message.getClientRequestMessage()
                            .getRequestType());
                    r.setUserId(message.getClientRequestMessage().getUserId());
                    r.setToken(message.getClientRequestMessage().getToken());
                    ctx.fireChannelRead(r);
                } else {
                    LOGGER.info("消息异常，ClientRequest message消息为null。");
                }
                break;
            case FIEL:
                if (message.getFileUpload() != null) {

                } else {
                    System.out.println("消息异常，FileUpload message消息为null。");
                }
                break;
            default:
                break;
        }
        ReferenceCountUtil.release(message);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("MsgChatHandler Connected from");
    }

    /**
     * 发生异常时如何处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        LOGGER.error("发生异常,用户将下线，session 将被移除，channel 将被关闭！");

        // 用户下线
        sessionManager.logout(ctx.channel());
        ctx.channel().close();
        ctx.close();
    }
}
