package com.lhs.service;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * TestUserService
 *
 * @author longhuashen
 * @since 15/9/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public final class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setAccount("lhs");
        user.setPassword("123");
        user.setNickName("路飞");
        user.setBirthday(new Date());
        user.setGender(1);
        user.setSignature("1111111111111111");
        user.setGender(1);
        userService.addUser(user);
    }

    @Test
    public void testGetUserByCount() {
        User user = userService.getUserByAccount("lhs");
        Assert.assertEquals("lhs", user.getAccount());
    }


    @Test
    public void testGetUserById() {
        User user = userService.getUserById(1);
        Assert.assertEquals(1, user.getId());
    }
}
