package com.lhs.weichat.service;

import com.lhs.weichat.bean.Attachement;

/**
 * IAttachemntService
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface IAttachemntService {

    Attachement getAttachementById(int id);

    /**
     * 根据附件组名和路径查找附件
     * @param groupName
     * @param path
     * @return
     */
    Attachement getAttachmentByGroupNameAndPath(String groupName, String path);

    /**
     * 保存附件
     * @param attachement
     * @return
     */
    Attachement saveAttachment(Attachement attachement);
}
