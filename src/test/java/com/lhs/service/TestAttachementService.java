package com.lhs.service;

import com.lhs.weichat.bean.Attachement;
import com.lhs.weichat.service.IAttachemntService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * TestAttachementService
 *
 * @author longhuashen
 * @since 15/9/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public final class TestAttachementService {

    @Autowired
    private IAttachemntService attachemntService;

    @Test
    public void testSave() {
        Attachement attachement = new Attachement();
        attachement.setName("苍老师");
        attachement.setGroupName("教育");
        attachement.setPath("/Users/test/");
        attachement.setType(1);
        attachement.setSize(10000l);
        attachement.setUserId(1);
        attachement.setToken("testToken123");
        attachement.setCreateDate(new Date());
        attachemntService.saveAttachment(attachement);
    }
}
