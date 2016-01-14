package com.lhs.service;

import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.service.IAttachmntService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(locations="classpath:applicationContext.xml")
public final class TestAttachmentService {

    @Autowired
    private IAttachmntService attachemntService;

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
        Attachment.setCreateDate(new Date());
        attachemntService.saveAttachment(Attachment);
    }
}
