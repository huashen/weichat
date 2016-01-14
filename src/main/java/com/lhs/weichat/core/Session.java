package com.lhs.weichat.core;

import io.netty.channel.Channel;

/**
 * Session
 *
 * @author longhuashen
 * @since 15/10/8
 */
public class Session {

    public enum SessionType {
        SC,
        SS
    }

    private Channel channel;

    private SessionType type;

    /**
     * 用户Id
     */
    private int userId;

    /**
     * 登录授权码
     */
    private String token;

    /**
     * 生成服务端对服务端的session
     * @param channel
     */
    public Session(Channel channel) {
        this.channel = channel;
        this.type = SessionType.SS;
    }

    /**
     * 生成服务端对客户端的session
     * @param channel
     * @param userId
     * @param token
     */
    public Session(Channel channel, int userId, String token) {
        this.channel = channel;
        this.type = SessionType.SC;
        this.userId =userId;
        this.token = token;
    }

    /**
     * 发送消息
     * @param msg
     */
    public void send(Object msg) {
        channel.writeAndFlush(msg);
    }

    public Channel getChannel() {
        return channel;
    }

    public SessionType getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
