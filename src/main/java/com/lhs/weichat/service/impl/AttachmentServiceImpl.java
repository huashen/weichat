package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.dao.IAttachmentDao;
import com.lhs.weichat.service.IAttachmntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttachmentServiceImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Service
public class AttachmentServiceImpl implements IAttachmntService {

    @Autowired
    private IAttachmentDao attachmentDao;

    @Override
    public Attachment getAttachmentById(int id) {
        return attachmentDao.getAttachmentById(id);
    }

    @Override
    public Attachment getAttachmentByGroupNameAndPath(String groupName, String path) {
        return attachmentDao.getAttachmentByGroupNameAndPath(groupName, path);
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
        return attachmentDao.saveAttachment(Attachment);
    }
}
