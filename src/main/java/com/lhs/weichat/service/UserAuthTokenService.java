package com.lhs.weichat.service;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;

import java.util.List;

/**
 * UserAuthTokenService
 *
 * @author longhuashen
 * @since 15/9/28
 */
public interface UserAuthTokenService {

    /**
     * 保存用户登录信息
     * @param user
     * @param clientId
     * @param clientType
     * @param token
     */
    void save(User user, String clientId, String clientType, String token);

    /**
     * 根据id和token获取userAuthToken
     * @param id
     * @param token
     * @return
     */
    UserAuthToken getUserAuthTokenByUserIdAndToken(int id, String token);

    /**
     * 获取用户授权信息
     *
     * @param userId
     * @return
     */
    List<UserAuthToken> getUserAuthTokenByUserId(int userId);

    /**
     * 通过认证码查找认证用户
     * @param token
     * @return
     */
    User getUserByToken(String token);
}
