package com.lhs.weichat.dao;

import com.lhs.weichat.bean.User;

/**
 * IUserDao
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface IUserDao {

    /**
     * 注册用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 根据账户名查找用户
     * @param account
     * @return
     */
    User getUserByAccount(String account);
}
