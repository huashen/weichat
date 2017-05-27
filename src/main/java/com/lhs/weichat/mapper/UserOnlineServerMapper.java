package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.UserOnlineServer;

/**
 * UserOnlineServerMapper
 *
 * @author longhuashen
 * @since 15/10/6
 */
public interface UserOnlineServerMapper {

    UserOnlineServer getOnlineServerByToken(int userId);
}
