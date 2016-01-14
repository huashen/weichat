package com.lhs.weichat.dao;

import com.lhs.weichat.bean.UserOnlineServer;

/**
 * IUserOnlineServerDao
 *
 * @author longhuashen
 * @since 15/10/6
 */
public interface IUserOnlineServerDao {

    UserOnlineServer getOnlineServerByToken(int userId);
}
