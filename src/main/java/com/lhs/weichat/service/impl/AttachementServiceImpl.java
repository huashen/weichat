package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Attachement;
import com.lhs.weichat.dao.IAttachementDao;
import com.lhs.weichat.service.IAttachemntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttachementServiceImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Service
public class AttachementServiceImpl implements IAttachemntService {

    @Autowired
    private IAttachementDao attachmentDao;

    public Attachement getAttachementById(int id) {
        return attachmentDao.getAttachementById(id);
    }

    public Attachement getAttachmentByGroupNameAndPath(String groupName, String path) {
        return attachmentDao.getAttachmentByGroupNameAndPath(groupName, path);
    }

    public Attachement saveAttachment(Attachement attachement) {
        if (attachement == null || attachement.getGroupName() == null || attachement.getGroupName().isEmpty()
                || attachement.getPath() == null || attachement.getPath().isEmpty()) {
            return null;
        }

        Attachement a = getAttachmentByGroupNameAndPath(attachement.getGroupName(), attachement.getPath());
        if(a != null) {
            return a;
        }
        return attachmentDao.saveAttachment(attachement);
    }
}
