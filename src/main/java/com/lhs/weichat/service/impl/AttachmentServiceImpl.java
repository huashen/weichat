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
        return attachmentMapper.getAttachmentById(id);
    }

    @Override
    public Attachment getAttachmentByGroupNameAndPath(String groupName, String path) {
        return attachmentMapper.getAttachmentByGroupNameAndPath(groupName, path);
    }

    @Override
    public Attachment saveAttachment(Attachment Attachment) {
        if (Attachment == null || Attachment.getGroupName() == null || Attachment.getGroupName().isEmpty()
                || Attachment.getPath() == null || Attachment.getPath().isEmpty()) {
            return null;
        }

        Attachment a = getAttachmentByGroupNameAndPath(Attachment.getGroupName(), Attachment.getPath());
        if(a != null) {
            return a;
        }
        return attachmentMapper.saveAttachment(Attachment);
    }
}
