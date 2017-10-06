package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.DiscussionGroup;

public interface DiscussionGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiscussionGroup record);

    int insertSelective(DiscussionGroup record);

    DiscussionGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiscussionGroup record);

    int updateByPrimaryKey(DiscussionGroup record);
}