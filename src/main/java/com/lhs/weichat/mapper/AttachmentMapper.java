package com.lhs.weichat.mapper;


import com.lhs.weichat.bean.Attachment;
import org.apache.ibatis.annotations.Param;

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

    Attachment getAttachmentByGroupNameAndPath(@Param("groupName") String groupName, @Param("path") String path);
}
