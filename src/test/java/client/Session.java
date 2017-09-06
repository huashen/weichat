package client;

import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.utils.StringUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session
 *
 * @author longhuashen
 * @since 17/9/3
 */
public class Session {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientHandler.class);
    
    private boolean firStart = true;
    
    public boolean reStarting = false;

    private SocketChannel socketChannel;

    private Bootstrap bootstrap;

    private String serverIp;

    private int serverPort;

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    private NettyClientHandler nettyClientHandler = new NettyClientHandler(this);

    private User user;
    private String token;

    public Session(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public boolean isReStarting() {
        return reStarting;
    }

    public boolean reConnect() {
        reStarting = true;
        socketChannel.pipeline().remove(IdleStateHandler.class);
        socketChannel.pipeline().remove(NettyClientHandler.class);
        ChannelFuture future = socketChannel.close();
        try {
            future.channel().closeFuture().sync();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        if (future.isSuccess()) {
            LOGGER.info(">>>>>>>>>>netty 成功关闭，准备重启!");
        } else {
            LOGGER.info(">>>>>>>>>>netty 关闭失败！ 准备重启!");
        }
        while (true) {
            // boolean r = reStart();
            boolean r = connect();
            if (r) {
                reStarting = false;
                return true;
            }
            try {
                Thread.sleep(1000);
                LOGGER.info(">>>>>>>>>>>>>>netty 睡眠一会儿再重启！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean connect() {
        if (firStart) {
            firStart = false;
        }
        bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(serverIp, serverPort);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel)
                    throws Exception {

                LOGGER.info(">>>>>>>>>netty initChannel 调用了！");
                socketChannel.pipeline().addLast("IdleStateHandler",
                        new IdleStateHandler(200, 100, 0));

                // decoded
                socketChannel.pipeline().addLast(
                        "LengthFieldBasedFrameDecoder",
                        new LengthFieldBasedFrameDecoder(10240, 0, 4, 0, 4));
                socketChannel.pipeline().addLast("ProtobufDecoder",
                        new ProtobufDecoder(Msg.Message.getDefaultInstance()));
                // encoded
                socketChannel.pipeline().addLast("LengthFieldPrepender",
                        new LengthFieldPrepender(4));
                socketChannel.pipeline().addLast("ProtobufEncoder",
                        new ProtobufEncoder());

                socketChannel.pipeline().addLast("ClassResolvers",
                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                socketChannel.pipeline().addLast(nettyClientHandler);

            }
        });

        try {
            ChannelFuture future = bootstrap.connect(serverIp, serverPort)
                    .sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                // 登录认证
//                auth();
                LOGGER.info(">>>>>>>>>>>netty 通讯服务启动成功!");
                return true;
            } else {
                LOGGER.info(">>>>>>>>>>>netty 通讯服务启动失败!");
                return false;
            }
        } catch (Exception e) {
            LOGGER.info(">>>>>>>>>>>>>>>netty 通讯服务启动失败!");
            LOGGER.info(">>>>>>>>>>>>>>>netty 通讯服务启动失败! {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String uuid, int contentType, String message,
                            int toId, int msgType, String fileGroupName, String filePath) {
        Msg.Message msg = null;

        switch (msgType) {
            case ChatMessage.MSG_TYPE_UU:
                msg = MsgHelper.newUUChatMessage(uuid, 1, toId,
                        message, "123", true,
                        StringUtils.getCurrentStringDate(), 0, contentType,
                        fileGroupName, filePath, ChatMessage.STATUS_SEND);
                break;
//            case ChatMessage.MSG_TYPE_UCG:
//                msg = MsgHelper.newUCGChatMessage(uuid, user.getId(), toId,
//                        message, token, true,
//                        StringUtils.getCurrentStringDate(), 0, contentType,
//                        fileGroupName, filePath, ChatMessage.STATUS_SEND);
//                break;
//            case ChatMessage.MSG_TYPE_UDG:
//                msg = MsgHelper.newUUChatMessage(uuid, user.getId(), toId,
//                        message, token, true,
//                        StringUtils.getCurrentStringDate(), 0, contentType,
//                        fileGroupName, filePath, ChatMessage.STATUS_SEND);
//                break;
        }
        socketChannel.writeAndFlush(msg);
    }

    /**
     * 发送认证消息
     */
    public void auth() {
        Msg.Message loginMsg = Msg.Message
                .newBuilder()
                .setClientLoginMessage(
                        Msg.ClientLoginMessage.newBuilder().setToken(token)
                                .setUserId(this.user.getId()).build())
                .setMessageType(Msg.MessageType.CLIENT_LOGIN).build();
        socketChannel.writeAndFlush(loginMsg);
    }
}
