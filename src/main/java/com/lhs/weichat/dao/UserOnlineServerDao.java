package com.lhs.weichat.dao;

import com.lhs.weichat.bean.UserOnlineServer;

/**
 * UserOnlineServerDao
 *
 * @author longhuashen
 * @since 15/10/6
 */
public interface UserOnlineServerDao {

    UserOnlineServer getOnlineServerByToken(int userId);
}
