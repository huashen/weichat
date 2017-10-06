package com.lhs.weichat.mapper;


import com.lhs.weichat.bean.Attachment;

/**
 * AttachmentMapper
 *
 * @author longhuashen
 * @since 15/9/24
 */
public interface AttachmentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKeyWithBLOBs(Attachment record);

    int updateByPrimaryKey(Attachment record);

    Attachment saveAttachment(Attachment Attachment);

    Attachment getAttachmentByGroupNameAndPath(String groupName, String path);

    Attachment getAttachmentById(int id);
}
