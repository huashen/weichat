package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.mapper.UserMapper;
import com.lhs.weichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public List<User> search(String condition) {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }
}
