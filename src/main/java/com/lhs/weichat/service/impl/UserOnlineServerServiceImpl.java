package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.bean.UserOnlineServer;
import com.lhs.weichat.mapper.ChatServerMapper;
import com.lhs.weichat.mapper.UserAuthTokenMapper;
import com.lhs.weichat.mapper.UserOnlineServerMapper;
import com.lhs.weichat.service.UserOnlineServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserOnlineServerService
 *
 * @author longhuashen
 * @since 15/10/6
 */
@Service
public class UserOnlineServerServiceImpl implements UserOnlineServerService {

    @Autowired
    private UserAuthTokenMapper userAuthTokenMapper;

    @Autowired
    private UserOnlineServerMapper userOnlineServerMapper;

    @Autowired
    private ChatServerMapper chatServerMapper;

    @Override
    public ChatServer getOnlineServer(int userId, String token) {
        UserAuthToken userAuthToken = userAuthTokenMapper.getUserAuthTokenByUserIdAndToken(userId, token);
        if(userAuthToken != null) {
            UserOnlineServer userOnlineServer = userOnlineServerMapper.getOnlineServerByToken(userAuthToken.getId());
            if (userOnlineServer != null) {
                return chatServerMapper.selectByPrimaryKey(userOnlineServer.getId());
            }
        }
        return null;
    }

    @Override
    public Set<ChatServer> getOnlineServer(int userId) {
        List<UserAuthToken> userAuthTokens = userAuthTokenMapper.getUserAuthTokenByUserId(userId);
        Set<ChatServer> set = new HashSet<>();
        if (userAuthTokens != null && userAuthTokens.size() > 0) {
            for (UserAuthToken userAuthToken : userAuthTokens) {
                UserOnlineServer userOnlineServer = userOnlineServerMapper.getOnlineServerByToken(userAuthToken.getId());
                if (userOnlineServer != null) {
                    Integer chatServerId = userOnlineServer.getChatServerId();
                    set.add(chatServerMapper.selectByPrimaryKey(chatServerId));
                }
            }
        }
        return set;
    }

    @Override
    public Set<UserAuthToken> getOnlineToken(int userId) {
        List<UserAuthToken> userAuthTokens = userAuthTokenMapper.getUserAuthTokenByUserId(userId);
        Set<UserAuthToken> set = new HashSet<>();
        if (userAuthTokens != null && userAuthTokens.size() > 0) {
            for (UserAuthToken userAuthToken : userAuthTokens) {
                UserOnlineServer userOnlineServer = userOnlineServerMapper.getOnlineServerByToken(userAuthToken.getId());
                if (userOnlineServer != null) {
                    set.add(userAuthToken);
                }
            }
        }
        return set;
    }

    @Override
    public void saveUserOnlineServer(int userId, String token, String ip, int port) {
        ChatServer chatServer = chatServerMapper.getChatServerByIpAndPort(ip, port);
        UserOnlineServer onlineServer = new UserOnlineServer();
        UserAuthToken userAuthToken = userAuthTokenMapper
                .getUserAuthTokenByUserIdAndToken(userId, token);
        UserOnlineServer userOnlineServer = userOnlineServerMapper.getUserOnlineServer(
                userAuthToken.getId(), chatServer.getId());
        if (userOnlineServer == null) {
            onlineServer.setChatServerId(chatServer.getId());
            onlineServer.setUserAuthTokenId(userAuthToken.getId());
            userOnlineServerMapper.insertSelective(onlineServer);
        }
    }

    @Override
    public void removeUserOnlineServer(int userId, String token, String ip, int port) {

    }
}
