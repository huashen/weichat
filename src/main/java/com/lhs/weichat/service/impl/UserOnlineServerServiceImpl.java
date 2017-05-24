package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.bean.UserOnlineServer;
import com.lhs.weichat.dao.UserAuthTokenDao;
import com.lhs.weichat.dao.UserOnlineServerDao;
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
    private UserAuthTokenDao userAuthTokenDao;

    @Autowired
    private UserOnlineServerDao userOnlineServerDao;

    @Override
    public ChatServer getOnlineServer(int userId, String token) {
        UserAuthToken userAuthToken = userAuthTokenDao.getUserAuthTokenByUserIdAndToken(userId, token);
        if(userAuthToken != null) {
            UserOnlineServer userOnlineServer = userOnlineServerDao.getOnlineServerByToken(userAuthToken.getId());
        }
        return null;
    }

    @Override
    public Set<ChatServer> getOnlineServer(int userId) {
        List<UserAuthToken> userAuthTokens = userAuthTokenDao.getUserAuthTokenByUserId(userId);
        Set<ChatServer> set = new HashSet<>();
        if (userAuthTokens != null && userAuthTokens.size() > 0) {
            for (UserAuthToken userAuthToken : userAuthTokens) {
                UserOnlineServer userOnlineServer = userOnlineServerDao.getOnlineServerByToken(userAuthToken.getId());
                if (userOnlineServer != null) {
                    //set.add(userOnlineServer.getChatServer());
                }
            }
        }
        return set;
    }

    @Override
    public Set<UserAuthToken> getOnlineToken(int userId) {
        List<UserAuthToken> userAuthTokens = userAuthTokenDao.getUserAuthTokenByUserId(userId);
        Set<UserAuthToken> set = new HashSet<>();
        if (userAuthTokens != null && userAuthTokens.size() > 0) {
            for (UserAuthToken userAuthToken : userAuthTokens) {
                UserOnlineServer userOnlineServer = userOnlineServerDao.getOnlineServerByToken(userAuthToken.getId());
                if (userOnlineServer != null) {
                    set.add(userAuthToken);
                }
            }
        }
        return set;
    }

    @Override
    public void saveUserOnlineServer(int userId, String token, String ip, int port) {

    }

    @Override
    public void removeUserOnlineServer(int userId, String token, String ip, int port) {

    }
}
