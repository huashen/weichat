package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.dao.IAttachmentDao;
import org.springframework.stereotype.Repository;

/**
 * AttachmentDaoImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Repository
public class AttachmentDaoImpl extends GenericDaoImpl implements IAttachmentDao {

    public Attachment saveAttachment(Attachment attachment) {
         this.insert("AttachmentMapper.saveAttachment", attachment);
         return attachment;
    }

    public Attachment getAttachmentByGroupNameAndPath(String groupName, String path) {
        Attachment attachment = new Attachment();
        attachment.setGroupName(groupName);
        attachment.setPath(path);
        return this.selectOne("AttachmentMapper.getAttachmentByGroupNameAndPath", attachment);
    }

    public Attachment getAttachmentById(int id) {
        return this.selectOne("AttachmentMapper.getAttachmentById", id);
    }
}
