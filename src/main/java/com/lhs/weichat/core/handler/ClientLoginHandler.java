package com.lhs.weichat.core.handler;

import com.lhs.weichat.core.NettyServerBootstrap;
import com.lhs.weichat.core.Session;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.TodoService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClientLoginHandler
 *
 * @author longhuashen
 * @since 17/5/21
 */
@Sharable
@Service
public class ClientLoginHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private TodoService todoService;

    private final Logger logger = LoggerFactory.getLogger(NettyServerBootstrap.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
System.out.println(msg);

        Msg.Message message = (Msg.Message) msg;

        Session session = sessionManager.clientLoginAuth(ctx,
                message.getClientLoginMessage().getToken(), message.getClientLoginMessage().getUserId());
        System.out.println("===============>用户登录操作");
        if (session != null) {

            Msg.Message m = MsgHelper.newResultMessage(
                    Msg.MessageType.LOGIN_SUCCESS, "登录成功！");
            session.send(m);
            // 发送代办消息
//            List<Todo> todos = todoService.getUnCompleteTodoByUserId(message
//                    .getUserId());
//            if (todos != null && todos.size() > 0) {
//                Msg.Message m2 = MsgHelper.newTodoListMessage(todos);
//                session.send(m2);
//            }
        } else {

            Msg.Message rtMessage = MsgHelper.newResultMessage(
                    Msg.MessageType.LOGIN_ERROR, "用户认证失败!");
            logger.error("用户登录失败，关闭。");
            ctx.channel().writeAndFlush(rtMessage);
            ctx.channel().close();
        }
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(">>>>>>>>>>>>>>>>>>>ClientLoginHandler exception caught：{}", cause);
        ctx.channel().close();
        ctx.close();
    }
}