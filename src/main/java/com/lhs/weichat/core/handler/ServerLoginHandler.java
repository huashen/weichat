package com.lhs.weichat.core.handler;

import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.ServerLoginMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ServerLoginHandler
 *
 * @author longhuashen
 * @since 17/10/4
 */
@ChannelHandler.Sharable
@Component
public class ServerLoginHandler extends SimpleChannelInboundHandler<ServerLoginMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerLoginHandler.class);

    @Autowired
    private SessionManager sessionManager;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ServerLoginMessage msg) throws Exception {
        LOGGER.info("服务器登录");
        if (!sessionManager.serverLogin(ctx)) {
            LOGGER.info("服务器登录失败，关闭。");
            ctx.channel().close();
            return;
        }
        ReferenceCountUtil.release(msg);
    }
}
