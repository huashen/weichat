package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.AppVersion;

public interface AppversionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKeyWithBLOBs(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    AppVersion getNewestVersion();
}