package com.lhs.service;

import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
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

//    @Override
//    protected void messageReceived(ChannelHandlerContext ctx, Msg.Message msg) throws Exception {
//        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
//
//        String ip = inetSocketAddress.getAddress().getHostAddress();
//        int port = inetSocketAddress.getPort();
//        logger.info("receive msg from server port:" + port);
//        logger.info("msgType:" + msg.getMessageType());
//        System.out.println("msgType:" + msg.getMessageType());
//
//        if (msg.getMessageType().equals(Msg.MessageType.CHAT_MESSAGE)) {
//            System.out.println("---------聊天信息-----------");
//            System.out.println("content:" + msg.getChatMessage().getContent());
//        }
//    }

    private void sendPing(ChannelHandlerContext context) {
        Msg.Message ping = MsgHelper.newPingMessage(Msg.MessageType.CLIENT_PING, "1");
        context.channel().writeAndFlush(ping);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    logger.info("服务器客户端：WRITER_IDLE");
                    sendPing(ctx);
                    logger.info("send ping to server");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Message msg) throws Exception {

    }
}
