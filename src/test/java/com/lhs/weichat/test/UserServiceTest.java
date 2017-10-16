package com.lhs.weichat.test;

import com.lhs.weichat.Application;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserServiceTest
 *
 * @author longhuashen
 * @since 17/10/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByAccount() {
        User user = userService.getUserByAccount("a");
        Assert.assertNull(user);
    }

}
