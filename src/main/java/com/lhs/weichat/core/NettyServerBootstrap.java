package com.lhs.weichat.core;

import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.handler.ClientLoginHandler;
import com.lhs.weichat.core.handler.MsgChatHandler;
import com.lhs.weichat.service.ChatServerService;
import com.lhs.weichat.utils.PropertyUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * NettyServerBootstrap
 *
 * @author longhuashen
 * @since 17/5/21
 */
@Service
public class NettyServerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(NettyServerBootstrap.class);

    private String ip;

    private int port;

    private String name;

    private ChatServerService chatServerService;

    @Autowired
    private ClientLoginHandler clientLoginHandler;

    @Autowired
    private MsgChatHandler msgChatHandler;

    @Autowired
    public NettyServerBootstrap(ChatServerService chatServerService) throws InterruptedException {
        this.chatServerService = chatServerService;
        this.ip = PropertyUtils.getValue("netty.server.ip");
        this.port = Integer.parseInt(PropertyUtils.getValue("netty.server.port"));
        this.name = PropertyUtils.getValue("netty.server.name");
        bind();
    }

    private void bind() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline channelPipeline = socketChannel.pipeline();
                socketChannel.pipeline().addLast(new IdleStateHandler(200, 100, 0));


                channelPipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                channelPipeline.addLast(new ProtobufDecoder(Msg.Message.getDefaultInstance()));

                channelPipeline.addLast(new LengthFieldPrepender(4));
                channelPipeline.addLast(new ProtobufEncoder());

                channelPipeline.addLast(msgChatHandler);
                channelPipeline.addLast(clientLoginHandler);
            }
        });

        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        if (channelFuture.isSuccess()) {
            chatServerService.register(ip, port, name);
            logger.info("server start on name:【{}】,ip：【{}】, port：【{}】", name, ip, port);
        }
    }
}
