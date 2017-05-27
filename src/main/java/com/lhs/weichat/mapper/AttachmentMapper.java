package com.lhs.weichat.mapper;


import com.lhs.weichat.bean.Attachment;

/**
 * AttachmentMapper
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface AttachmentMapper {

    Attachment saveAttachment(Attachment Attachment);

    Attachment getAttachmentByGroupNameAndPath(String groupName, String path);

    Attachment getAttachmentById(int id);
}
