package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IUserDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * UserDaoImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

    public User addUser(User user) {
        Integer id = (Integer) this.save(user);
        user.setId(id);
        return user;
    }

    public User getUserByAccount(String account) {
        String hql = "from User where account =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, account);
        User user = (User) query.uniqueResult();
        return user;
    }
}
