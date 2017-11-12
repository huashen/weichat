package com.lhs.weichat.core.handler;

import com.lhs.weichat.core.Session;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.core.bean.PingMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * ServerClientHandler
 *
 * @author longhuashen
 * @since 17/11/12
 */
@Service
public class ServerClientHandler extends SimpleChannelInboundHandler<PingMessage> {

    @Autowired
    private SessionManager sessionManager;

    // 本地日志记录对象
    private static final Logger LOGGER = Logger.getLogger(ServerClientHandler.class);

    /**
     * 当出现WRITER_IDLE、ALL_IDLE时则需要向客户端发出ping消息，试探远端是否还在
     */
    private void sendPing(ChannelHandlerContext ctx) {
        Session session = SessionManager.get(ctx.channel());
        if (session != null) {

            Msg.Message ping = MsgHelper.newPingMessage(
                    Msg.MessageType.SERVER_PING, sessionManager.getIp() + ":"
                            + sessionManager.getPort());
            session.send(ping);
            ctx.fireChannelRead(ping);
        }

    }

    // 利用写空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    LOGGER.info("服务器客户端：WRITER_IDLE");
                    sendPing(ctx);
                    LOGGER.info("服务器客户端 send ping to server。");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PingMessage msg) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx
                .channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        LOGGER.info("服务器客户端receive ping from server port:" + port);
        LOGGER.info("msgType:" + msg.getMessageType());
        System.out.println("msgType:" + msg.getMessageType());
        ReferenceCountUtil.release(msg);
    }
}
