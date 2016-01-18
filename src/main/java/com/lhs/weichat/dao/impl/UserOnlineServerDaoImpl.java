package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.UserOnlineServer;
import com.lhs.weichat.dao.IUserOnlineServerDao;
import org.springframework.stereotype.Repository;

/**
 * UserOnlineServerDao
 *
 * @author longhuashen
 * @since 15/10/6
 */
@Repository
public class UserOnlineServerDaoImpl extends GenericDaoImpl implements IUserOnlineServerDao {

    @Override
    public UserOnlineServer getOnlineServerByToken(int userId) {
        return null;
    }
}
