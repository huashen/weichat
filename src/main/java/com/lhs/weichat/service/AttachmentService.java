package com.lhs.weichat.service;

import com.lhs.weichat.bean.Attachment;

/**
 * AttachmentService
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface AttachmentService {

    /**
     * 根据id获取附件
     *
     * @param id
     * @return
     */
    Attachment getAttachmentById(int id);

    /**
     * 根据附件组名和路径查找附件
     * @param groupName
     * @param path
     * @return
     */
    Attachment getAttachmentByGroupNameAndPath(String groupName, String path);

    /**
     * 保存附件
     * @param Attachment
     * @return
     */
    Attachment saveAttachment(Attachment Attachment);
}
