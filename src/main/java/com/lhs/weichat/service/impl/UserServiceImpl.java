package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.IUserDao;
import com.lhs.weichat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }
}
