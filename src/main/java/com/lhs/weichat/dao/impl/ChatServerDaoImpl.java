package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.dao.ChatServerDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ChatServerDao
 *
 * @author longhuashen
 * @since 15/10/3
 */
@Repository
public class ChatServerDaoImpl extends GenericDaoImpl implements ChatServerDao {

    @Override
    public List<ChatServer> getOnlineServer() {
        return null;
    }

    @Override
    public ChatServer getChatServerByIpAndPort(String ip, int port) {
        return null;
    }

    @Override
    public void update(ChatServer server) {

    }

    @Override
    public void insert(ChatServer server) {

    }
}
