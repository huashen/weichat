package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.ChatServer;

import java.util.List;

/**
 * ChatServerMapper
 *
 * @author longhuashen
 * @since 15/10/3
 */
public interface ChatServerMapper {


    List<ChatServer> getOnlineServer();

    ChatServer getChatServerByIpAndPort(String ip, int port);

    void update(ChatServer server);

    void insert(ChatServer server);
}
