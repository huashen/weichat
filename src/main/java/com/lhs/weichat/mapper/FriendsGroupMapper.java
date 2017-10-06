package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.FriendsGroup;

import java.util.List;

public interface FriendsGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendsGroup record);

    int insertSelective(FriendsGroup record);

    FriendsGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendsGroup record);

    int updateByPrimaryKey(FriendsGroup record);

    List<FriendsGroup> getFriendsGroupByUserId(int id);
}