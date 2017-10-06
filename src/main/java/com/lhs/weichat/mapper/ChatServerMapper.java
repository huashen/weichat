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

    int deleteByPrimaryKey(Integer id);

    int insert(ChatServer record);

    int insertSelective(ChatServer record);

    ChatServer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatServer record);

    int updateByPrimaryKey(ChatServer record);


    List<ChatServer> getOnlineServer();

    ChatServer getChatServerByIpAndPort(String ip, int port);

    void update(ChatServer server);

}
