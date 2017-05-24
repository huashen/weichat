package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.dao.ChatServerDao;
import com.lhs.weichat.service.ChatServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChatServerService
 *
 * @author longhuashen
 * @since 15/10/3
 */
@Service
public class ChatServerServiceImpl implements ChatServerService {

    @Autowired
    private ChatServerDao chatServerDao;

    @Override
    public List<ChatServer> getOnlineServer() {
        return chatServerDao.getOnlineServer();
    }

    @Override
    public ChatServer getChatServerByIpAndPort(String ip, int port) {
        return chatServerDao.getChatServerByIpAndPort(ip, port);
    }

    @Override
    public void register(String ip, int port, String name) {
//        ChatServer server = chatServerDao.getChatServerByIpAndPort(ip, port);
//        if(server != null && server.getId() > 0) {
//            if(!server.isOnline())
//                server.setOnLine(true);
//                chatServerDao.update(server);
//            }
//        }else {
//            server = new ChatServer();
//            server.setIp(ip);
//            server.setPort(port);
//            server.setName(name);
//            server.setOnLine(true);
//            chatServerDao.insert(server);
//        }
    }

    @Override
    public void offline(String ip, int port) {
        ChatServer server = chatServerDao.getChatServerByIpAndPort(ip, port);
        if(server != null && server.getId() > 0) {
            if(server.isOnline()) {
                server.setOnLine(false);
                chatServerDao.update(server);
            }
        }
    }
}
