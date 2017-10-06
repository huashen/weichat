package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.GroupClass;

public interface GroupClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupClass record);

    int insertSelective(GroupClass record);

    GroupClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupClass record);

    int updateByPrimaryKey(GroupClass record);
}