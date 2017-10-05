package com.lhs.weichat.core;

import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.handler.ChatHandler;
import com.lhs.weichat.core.handler.ClientLoginHandler;
import com.lhs.weichat.core.handler.ClientRequestHandler;
import com.lhs.weichat.core.handler.FileUploadHandler;
import com.lhs.weichat.core.handler.MsgChatHandler;
import com.lhs.weichat.core.handler.PingHandler;
import com.lhs.weichat.core.handler.ServerLoginHandler;
import com.lhs.weichat.service.ChatServerService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * NettyServerBootstrap
 *
 * netty服务端启动类
 *
 * @author longhuashen
 * @since 17/5/21
 */
@Service
public class NettyServerBootstrap implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(NettyServerBootstrap.class);

    @Value("${netty.server.ip}")
    private String ip;

    @Value("${netty.server.port}")
    private int port;

    @Value("${netty.server.name}")
    private String name;

    private ChatServerService chatServerService;

    @Autowired
    private ClientLoginHandler clientLoginHandler;

    @Autowired
    private MsgChatHandler msgChatHandler;

    @Autowired
    private PingHandler pingHandler;

    @Autowired
    private ChatHandler chatHandler;

    @Autowired
    private ServerLoginHandler serverLoginHandler;

    @Autowired
    private ClientRequestHandler clientRequestHandler;

    @Autowired
    private FileUploadHandler fileUploadHandler;

    @Autowired
    public NettyServerBootstrap(ChatServerService chatServerService) throws InterruptedException {
        this.chatServerService = chatServerService;
    }

    private void bind() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        // 保持长连接状态
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();

                pipeline.addLast(
                        new IdleStateHandler(200, 100, 0));
                pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
                pipeline.addLast("protobufDecoder", new ProtobufDecoder(Msg.Message.getDefaultInstance()));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("protobufEncoder", new ProtobufEncoder());

                pipeline.addLast(clientLoginHandler);
                pipeline.addLast(serverLoginHandler);
                pipeline.addLast(pingHandler);
                pipeline.addLast(chatHandler);
                pipeline.addLast(clientRequestHandler);
                pipeline.addLast(msgChatHandler);
                pipeline.addLast(fileUploadHandler);
            }
        });

        ChannelFuture channelFuture = bootstrap.bind(this.port).sync();
        if (channelFuture.isSuccess()) {
            chatServerService.register(ip, port, name);
            logger.info("server start on name:【{}】,ip：【{}】, port：【{}】", name, ip, port);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.bind();
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
