package com.lhs.weichat.dao;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;

import java.util.List;

/**
 * IUserAuthTokenDao
 *
 * @author longhuashen
 * @since 15/9/28
 */
public interface IUserAuthTokenDao {

    UserAuthToken getUserAuthTokenByUserIdAndToken(int userId, String token);

    List<UserAuthToken> getUserAuthTokenByUserId(int userId);

    User getUserByToken(String token);

    void saveUserAuthToken(UserAuthToken userAuthToken);
}
