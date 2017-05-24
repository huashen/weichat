package com.lhs.weichat.dao;

import com.lhs.weichat.bean.ChatServer;

import java.util.List;

/**
 * ChatServerDao
 *
 * @author longhuashen
 * @since 15/10/3
 */
public interface ChatServerDao {


    List<ChatServer> getOnlineServer();

    ChatServer getChatServerByIpAndPort(String ip, int port);

    void update(ChatServer server);

    void insert(ChatServer server);
}
