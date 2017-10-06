package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.UserOnlineServer;

/**
 * UserOnlineServerMapper
 *
 * @author longhuashen
 * @since 15/10/6
 */
public interface UserOnlineServerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserOnlineServer record);

    int insertSelective(UserOnlineServer record);

    UserOnlineServer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOnlineServer record);

    int updateByPrimaryKey(UserOnlineServer record);

    UserOnlineServer getOnlineServerByToken(int userId);
}
