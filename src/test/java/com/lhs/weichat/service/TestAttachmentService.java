package com.lhs.weichat.service;

import com.lhs.weichat.Application;
import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.service.AttachmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * TestAttachmentService
 *
 * @author longhuashen
 * @since 15/9/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public final class TestAttachmentService {

    @Autowired
    private AttachmentService attachemntService;

    @Test
    public void testSave() {
        Attachment Attachment = new Attachment();
        Attachment.setName("苍老师");
        Attachment.setGroupName("教育");
        Attachment.setPath("/Users/test/");
        Attachment.setType(1);
        Attachment.setSize(10000l);
        Attachment.setUserId(1);
        Attachment.setToken("testToken123");
        Attachment.setCreateTime(new Date());
        attachemntService.saveAttachment(Attachment);
    }

    @Test
    public void testGetAttachmentById() {
        Attachment attachment = attachemntService.getAttachmentById(1);
        Assert.assertEquals(1, attachment.getId());
    }
}