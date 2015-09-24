package com.lhs.service;

import com.lhs.weichat.bean.Attachement;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.service.IAttachemntService;
import com.lhs.weichat.service.IUserService;
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
    private IUserService userService;

    @Autowired
    private IAttachemntService attachemntService;

    @Test
    public void addUser() {
        User user = new User();
        user.setAccount("lhs");
        user.setPassword("123");
        user.setName("路飞");
        user.setBirthday(new Date());
        user.setGender(1);
        user.setSignature("1111111111111111");
        Attachement attachement = attachemntService.getAttachementById(1);
        System.out.println(attachement.getName());
        user.setAvatar(attachement);
        user.setGender(1);
        userService.addUser(user);
    }
}
