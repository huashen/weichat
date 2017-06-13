package com.lhs.weichat.service;

import com.lhs.weichat.core.Constants;
import com.lhs.weichat.core.bean.Msg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
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
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * NettyClientBootstrap
 *
 * @author longhuashen
 * @since 15/10/12
 */
public class NettyClientBootstrap {

    private int port;
    private String host;
    private SocketChannel socketChannel;
    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(
            20);

    public NettyClientBootstrap(int port, String host)
            throws InterruptedException {
        this.port = port;
        this.host = host;
        start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(host, port);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel)
                    throws Exception {
                socketChannel.pipeline().addLast(
                        new IdleStateHandler(20, 10, 0));

                // decoded
                socketChannel.pipeline().addLast(
                        new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                socketChannel.pipeline().addLast(
                        new ProtobufDecoder(Msg.Message.getDefaultInstance()));
                // encoded
                socketChannel.pipeline().addLast(new LengthFieldPrepender(4));
                socketChannel.pipeline().addLast(new ProtobufEncoder());

                socketChannel.pipeline().addLast(
                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                socketChannel.pipeline().addLast(new NettyClientHandler());
            }
        });
        ChannelFuture future = bootstrap.connect(host, 8888).sync();

        if (future.isSuccess()) {
            socketChannel = (SocketChannel) future.channel();
            System.out.println("connect server  成功---------");

        }

        Constants.setClientId("001");


        Msg.Message loginMsg = Msg.Message
                .newBuilder()
                .setClientLoginMessage(
                        Msg.ClientLoginMessage.newBuilder().setToken("xxxx")
                                .setUserId(1).build())
                .setMessageType(Msg.MessageType.CLIENT_LOGIN).build();

        Channel channel = future.awaitUninterruptibly().channel();
        channel.writeAndFlush(loginMsg).awaitUninterruptibly();

    }

    public static void main(String[] args) throws InterruptedException, IOException {
//        Constants.setClientId("001");
//        NettyClientBootstrap bootstrap = new NettyClientBootstrap(8888,
//                "localhost");
//
//        Msg.Message loginMsg = Msg.Message
//                .newBuilder()
//                .setClientLoginMessage(
//                        Msg.ClientLoginMessage.newBuilder().setToken("xxxx")
//                                .setUserId(1).build())
//                .setMessageType(Msg.MessageType.CLIENT_LOGIN).build();
//
//        channel = future.awaitUninterruptibly().channel();
//        bootstrap.socketChannel.writeAndFlush(loginMsg).awaitUninterruptibly();

//        NettyClientBootstrap bootstrap = new NettyClientBootstrap(8888,
//                "localhost");

        Socket socket = null;
        DataOutputStream out = null;
        DataInputStream in = null;

        try {

            socket = new Socket("localhost", 8888);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            Msg.Message loginMsg = Msg.Message
                    .newBuilder()
                    .setClientLoginMessage(
                            Msg.ClientLoginMessage.newBuilder().setToken("xxxx")
                                    .setUserId(1).build())
                    .setMessageType(Msg.MessageType.CLIENT_LOGIN).build();

            byte[] outputBytes = loginMsg.toByteArray(); // Student转成字节码
            out.writeInt(outputBytes.length); // write header
            out.write(outputBytes); // write body
            out.flush();

        } finally {
            // 关闭连接
            in.close();
            out.close();
            socket.close();
        }
    }
}
