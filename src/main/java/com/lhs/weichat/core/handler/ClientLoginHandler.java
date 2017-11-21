package com.lhs.weichat.core.handler;

import com.lhs.weichat.bean.Todo;
import com.lhs.weichat.core.Session;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.ClientLoginMessage;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.TodoService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClientLoginHandler
 *
 * @author longhuashen
 * @since 17/5/21
 */
@Sharable
@Component
public class ClientLoginHandler extends SimpleChannelInboundHandler<ClientLoginMessage> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private TodoService todoService;

    private final Logger logger = LoggerFactory.getLogger(ClientLoginHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ClientLoginMessage message) throws Exception {
System.out.println(">>>>>ClientLoginHandler channelRead0:" + message);
        Session session = sessionManager.clientLoginAuth(channelHandlerContext,
                message.getToken(), message.getUserId());
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
            logger.info("用户登录失败，关闭。");
            channelHandlerContext.channel().writeAndFlush(rtMessage);
            channelHandlerContext.channel().close();
        }
        ReferenceCountUtil.release(message);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("ClientLoginHandler Connected from");
    }
}
