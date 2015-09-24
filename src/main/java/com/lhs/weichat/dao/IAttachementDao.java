package com.lhs.weichat.dao;

import com.lhs.weichat.bean.Attachement;

/**
 * IAttachementDao
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface IAttachementDao {

    Attachement saveAttachment(Attachement attachement);

    Attachement getAttachmentByGroupNameAndPath(String groupName, String path);

    Attachement getAttachementById(int id);
}
