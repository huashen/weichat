package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.mapper.ChatServerMapper;
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
@Service("chatServerService")
public class ChatServerServiceImpl implements ChatServerService {

//    @Autowired
    private ChatServerMapper chatServerMapper;

    @Override
    public List<ChatServer> getOnlineServer() {
        return chatServerMapper.getOnlineServer();
    }

    @Override
    public ChatServer getChatServerByIpAndPort(String ip, int port) {
        return chatServerMapper.getChatServerByIpAndPort(ip, port);
    }

    @Override
    public void register(String ip, int port, String name) {
//        ChatServer server = chatServerMapper.getChatServerByIpAndPort(ip, port);
//        if(server != null && server.getId() > 0) {
//            if(!server.isOnline())
//                server.setOnLine(true);
//                chatServerMapper.update(server);
//            }
//        }else {
//            server = new ChatServer();
//            server.setIp(ip);
//            server.setPort(port);
//            server.setName(name);
//            server.setOnLine(true);
//            chatServerMapper.insert(server);
//        }
    }

    @Override
    public void offline(String ip, int port) {
        ChatServer server = chatServerMapper.getChatServerByIpAndPort(ip, port);
        if(server != null && server.getId() > 0) {
            if(server.isOnline()) {
                server.setOnLine(false);
                chatServerMapper.update(server);
            }
        }
    }
}
