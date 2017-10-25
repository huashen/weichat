package com.lhs.weichat.service;

import com.lhs.weichat.bean.ChatServer;

import java.util.List;

/**
 * ChatServerService
 *
 * @author longhuashen
 * @since 15/10/3
 */
public interface ChatServerService {

    /**
     * 得到所有的在线Server
     *
     * @return
     */
    List<ChatServer> getOnlineServer();


    /**
     * 根据ip和端口获取服务器
     *
     * @param ip
     * @param port
     * @return
     */
    ChatServer getChatServerByIpAndPort(String ip, int port);

    /**
     * 当服务器启动成功后将自己注册到系统中
     * 1、如果数据库中存在该服务器则设置上线状态为true
     * 2、如果不存在则插入，设置上线状态为true
     *
     * @param ip
     * @param port
     * @param name
     */
    void register(String ip, int port, String name);

    /**
     * 将已经上线的服务器下线
     *
     * @param ip
     * @param port
     */
    void offline(String ip, int port);
}
