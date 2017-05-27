package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.mapper.UserAuthTokenMapper;
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
    private UserAuthTokenMapper userAuthTokenMapper;

    @Override
    public void save(User user, String clientId, String clientType, String token) {
        UserAuthToken userAuthToken = userAuthTokenMapper.getUserAuthTokenByUserIdAndToken(user.getId(), clientId);
        if (userAuthToken == null) {
            userAuthToken = new UserAuthToken();
            userAuthToken.setUser(user);
            userAuthToken.setClientId(clientId);
        }
        userAuthToken.setEnable(true);
        userAuthToken.setCreateDate(new Date());
        userAuthToken.setToken(token);
        userAuthToken.setClientType(clientType);
        userAuthTokenMapper.saveUserAuthToken(userAuthToken);
    }

    @Override
    public UserAuthToken getUserAuthTokenByUserIdAndToken(int id, String token) {
        return userAuthTokenMapper.getUserAuthTokenByUserIdAndToken(id, token);
    }

    @Override
    public List<UserAuthToken> getUserAuthTokenByUserId(int userId) {
        return userAuthTokenMapper.getUserAuthTokenByUserId(userId);
    }

    @Override
    public User getUserByToken(String token) {
        return userAuthTokenMapper.getUserByToken(token);
    }
}
