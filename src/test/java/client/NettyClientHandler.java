package client;

import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.Msg.Message;
import com.lhs.weichat.core.bean.MsgHelper;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * NettyClientHandler
 *
 * @author longhuashen
 * @since 17/9/3
 */
@Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientHandler.class);

    private int pingTimes = 0;

    private Session session;

    public NettyClientHandler(Session session) {
        this.session = session;
    }


    /**
     * 当出现WRITER_IDLE、ALL_IDLE时则需要向客户端发出ping消息，试探远端是否还在
     */
    private void sendPing(ChannelHandlerContext ctx) {
        // Session session = SessionManager.get(ctx.channel());

        Message ping = MsgHelper.newPingMessage(Msg.MessageType.CLIENT_PING,
                session.getUser().getId() + session.getToken());
        ctx.channel().writeAndFlush(ping);

        if (pingTimes > 5 && !session.isReStarting()) {
            boolean r = session.reConnect();
            if (r) {
                pingTimes = 0;
            }
            System.out.println("重连结果：" + r);
        }

        pingTimes++;
    }

    // 利用写空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    LOGGER.info("org.weishe.weichat", "服务器客户端：WRITER_IDLE");
                    sendPing(ctx);
                    LOGGER.info("org.weishe.weichat", "服务器客户端 send ping to server。");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                   Message baseMsg) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) channelHandlerContext
                .channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        LOGGER.info("org.weishe.weichat.msg", "receive msg from server port:" + port);
        LOGGER.info("org.weishe.weichat.msg", "msgType:" + baseMsg.getMessageType());
        switch (baseMsg.getMessageType()) {
            case CHAT_MESSAGE:
                LOGGER.info("org.weishe.weichat", "-------------聊天信息---------------");
                LOGGER.info("org.weishe.weichat", "|from:"
                        + baseMsg.getChatMessage().getFromId());
                LOGGER.info("org.weishe.weichat", "|to:"
                        + baseMsg.getChatMessage().getToId());
                LOGGER.info("org.weishe.weichat", "|token:"
                        + baseMsg.getChatMessage().getToken());
                LOGGER.info("org.weishe.weichat", "|content:"
                        + baseMsg.getChatMessage().getContent());
                LOGGER.info("org.weishe.weichat", "|from:"
                        + baseMsg.getChatMessage().getFromId());
                LOGGER.info("org.weishe.weichat", "---------------------------------");
                break;
            case FRIENDS_LIST:

                break;
            case FRIENDS_GROUP_LIST:
                break;
            case CHAT_MESSAGE_LIST:
                // 历史消息
                List<Msg.ChatMessage> cmg = baseMsg.getChatMessageListMessageList();
                break;
            case JSON_DATA:

                break;
            case TODO:

                break;
            case TODO_MESSAGE_LIST:

                break;
            case CHAT_GROUP_LIST:

                break;
            case DISCUSSION_GROUP_LIST:

                break;
            case SERVER_PONG:
                pingTimes = 0;
                break;
            case CLIENT_PONG:
                pingTimes = 0;
                break;
            case AUTH_ERROR:

                break;
            case FIEL:
                break;
            case USER_LIST:
                List<Msg.User> ul = baseMsg.getUserListMessageList();
                // 转发给FriendsGroupListHandler处理
                channelHandlerContext.fireChannelRead(ul);
                break;
            case RECEIPT:
                // 消息回执
                Msg.ReceiptMessage rm = baseMsg.getReceiptMessage();
                // 更新本地消息状态
                break;
            default:
                break;
        }

        ReferenceCountUtil.release(baseMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        LOGGER.info("org.weishe.weichat", "发生异常了");

    }
}