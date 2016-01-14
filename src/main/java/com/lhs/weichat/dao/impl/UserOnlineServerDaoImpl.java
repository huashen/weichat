package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.UserOnlineServer;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IUserOnlineServerDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * UserOnlineServerDao
 *
 * @author longhuashen
 * @since 15/10/6
 */
@Repository
public class UserOnlineServerDaoImpl extends BaseDao implements IUserOnlineServerDao {

    @Override
    public UserOnlineServer getOnlineServerByToken(int userId) {
        String hql = "from UserOnlineServer u where u.userAuthToken.id =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return (UserOnlineServer) query.uniqueResult();
    }
}
