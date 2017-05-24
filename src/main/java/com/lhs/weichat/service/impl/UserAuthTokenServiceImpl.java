package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.dao.UserAuthTokenDao;
import com.lhs.weichat.service.UserAuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * UserAuthTokenServiceImpl
 *
 * @author longhuashen
 * @since 15/9/28
 */
@Service
public class UserAuthTokenServiceImpl implements UserAuthTokenService {

    @Autowired
    private UserAuthTokenDao userAuthTokenDao;

    @Override
    public void save(User user, String clientId, String clientType, String token) {
        UserAuthToken userAuthToken = userAuthTokenDao.getUserAuthTokenByUserIdAndToken(user.getId(), clientId);
        if (userAuthToken == null) {
            userAuthToken = new UserAuthToken();
            userAuthToken.setUser(user);
            userAuthToken.setClientId(clientId);
        }
        userAuthToken.setEnable(true);
        userAuthToken.setCreateDate(new Date());
        userAuthToken.setToken(token);
        userAuthToken.setClientType(clientType);
        userAuthTokenDao.saveUserAuthToken(userAuthToken);
    }

    @Override
    public UserAuthToken getUserAuthTokenByUserIdAndToken(int id, String token) {
        return userAuthTokenDao.getUserAuthTokenByUserIdAndToken(id, token);
    }

    @Override
    public List<UserAuthToken> getUserAuthTokenByUserId(int userId) {
        return userAuthTokenDao.getUserAuthTokenByUserId(userId);
    }

    @Override
    public User getUserByToken(String token) {
        return userAuthTokenDao.getUserByToken(token);
    }
}
