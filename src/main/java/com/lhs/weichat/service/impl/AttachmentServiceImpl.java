package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.mapper.AttachmentMapper;
import com.lhs.weichat.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttachmentServiceImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public Attachment getAttachmentById(int id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Attachment getAttachmentByGroupNameAndPath(String groupName, String path) {
        return attachmentMapper.getAttachmentByGroupNameAndPath(groupName, path);
    }

    @Override
    public Attachment saveAttachment(Attachment attachment) {
        if (attachment == null || attachment.getGroupName() == null || attachment.getGroupName().isEmpty()
                || attachment.getPath() == null || attachment.getPath().isEmpty()) {
            return null;
        }

        Attachment a = this.getAttachmentByGroupNameAndPath(attachment.getGroupName(), attachment.getPath());
        if(a != null) {
            return a;
        }

        int num = attachmentMapper.insertSelective(attachment);
        if (num < 1) {
            return null;
        }
        return attachmentMapper.selectByPrimaryKey(attachment.getId());
    }
}
