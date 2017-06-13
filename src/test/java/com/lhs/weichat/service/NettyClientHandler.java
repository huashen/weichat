package com.lhs.weichat.service;

import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * NettyClientHandler
 *
 * @author longhuashen
 * @since 15/10/12
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<Msg.Message> {

    private static Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    /**
	 * 当出现WRITER_IDLE、ALL_IDLE时则需要向客户端发出ping消息，试探远端是否还在
	 */
    private void sendPing(ChannelHandlerContext ctx) {
        // Session session = SessionManager.get(ctx.channel());

        Msg.Message ping = MsgHelper.newPingMessage(Msg.MessageType.CLIENT_PING,
                "1");
        ctx.channel().writeAndFlush(ping);

    }

    // 利用写空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    logger.info("服务器客户端：WRITER_IDLE");
                    sendPing(ctx);
                    logger.info("服务器客户端 send ping to server。");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg.Message baseMsg) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) channelHandlerContext
                .channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        logger.info("receive msg from server port:" + port);
        logger.info("msgType:" + baseMsg.getMessageType());
        System.out.println("msgType:" + baseMsg.getMessageType());
        if (baseMsg.getMessageType().equals(Msg.MessageType.CHAT_MESSAGE)) {
            System.out.println("-------------聊天信息---------------");
            System.out.println("|from:" + baseMsg.getChatMessage().getFromId());
            System.out.println("|to:" + baseMsg.getChatMessage().getToId());
            System.out.println("|token:" + baseMsg.getChatMessage().getToken());
            System.out.println("|content:"
                    + baseMsg.getChatMessage().getContent());
            System.out.println("|from:" + baseMsg.getChatMessage().getFromId());
            System.out.println("---------------------------------");
        }
		/*
		 * switch (msgType) { case LOGIN: { // 向服务器发起登录 LoginMsg loginMsg = new
		 * LoginMsg(); loginMsg.setPassword("yao");
		 * loginMsg.setUserName("robin");
		 * channelHandlerContext.writeAndFlush(loginMsg); } break; case PING: {
		 * LOGGER.info("receive ping from server----------"); } break; case ASK:
		 * { ReplyClientBody replyClientBody = new ReplyClientBody(
		 * "client info **** !!!"); ReplyMsg replyMsg = new ReplyMsg();
		 * replyMsg.setBody(replyClientBody);
		 * channelHandlerContext.writeAndFlush(replyMsg); } break; case REPLY: {
		 * ReplyMsg replyMsg = (ReplyMsg) baseMsg; ReplyServerBody
		 * replyServerBody = (ReplyServerBody) replyMsg .getBody();
		 * LOGGER.info("receive client msg: " +
		 * replyServerBody.getServerInfo()); } default: break; }
		 */
        ReferenceCountUtil.release(baseMsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
    }
}
