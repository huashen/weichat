package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.dao.IUserAuthTokenDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserAuthTokenDao
 *
 * @author longhuashen
 * @since 15/9/28
 */
@Repository
public class UserAuthTokenDaoImpl extends GenericDaoImpl implements IUserAuthTokenDao {

    @Override
    public UserAuthToken getUserAuthTokenByUserIdAndToken(int userId, String token) {
        return null;
    }

    @Override
    public List<UserAuthToken> getUserAuthTokenByUserId(int userId) {
        return null;
    }

    @Override
    public User getUserByToken(String token) {
        return null;
    }

    @Override
    public void saveUserAuthToken(UserAuthToken userAuthToken) {

    }
}
