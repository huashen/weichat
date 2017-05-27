package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.User;

/**
 * UserMapper
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface UserMapper {

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
//
//    /**
//     * 查找用户
//     * @param condition
//     * @return
//     */
//    List<User> search(String condition);

    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    User getUserById(int userId);
}
