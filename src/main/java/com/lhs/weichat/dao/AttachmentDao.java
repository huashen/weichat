package com.lhs.weichat.dao;


import com.lhs.weichat.bean.Attachment;

/**
 * AttachmentDao
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface AttachmentDao {

    Attachment saveAttachment(Attachment Attachment);

    Attachment getAttachmentByGroupNameAndPath(String groupName, String path);

    Attachment getAttachmentById(int id);
}
