package com.lhs.weichat.service;

import com.lhs.weichat.bean.User;

/**
 * IUserService
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface IUserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 根据账户名称查找用户
     * @param account
     * @return
     */
    User getUserByAccount(String account);
}
