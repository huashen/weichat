package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IUserAuthTokenDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserAuthTokenDao
 *
 * @author longhuashen
 * @since 15/9/28
 */
@Repository
public class UserAuthTokenDaoImpl extends BaseDao implements IUserAuthTokenDao {

    @Override
    public UserAuthToken getUserAuthTokenByUserIdAndToken(int userId, String token) {
        String hql = "from UserAuthToken where token =? and enable =? and userId =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, token);
        query.setParameter(1, true);
        query.setParameter(2, userId);
        return (UserAuthToken) query.uniqueResult();
    }

    @Override
    public List<UserAuthToken> getUserAuthTokenByUserId(int userId) {
        String hql = "from UserAuthToken where enable =? and userId =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, true);
        query.setParameter(1, true);
        return query.list();
    }

    @Override
    public User getUserByToken(String token) {
        String hql = "from UserAuthToken where token =? and enable =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, token);
        query.setParameter(1, true);
        UserAuthToken userAuthToken = (UserAuthToken) query.uniqueResult();
        return userAuthToken.getUser();
    }

    @Override
    public void saveUserAuthToken(UserAuthToken userAuthToken) {
        this.save(userAuthToken);
    }
}
