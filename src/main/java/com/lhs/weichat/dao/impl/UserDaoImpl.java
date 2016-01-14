package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IUserDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * UserDaoImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

    @Override
    public User addUser(User user) {
        Integer id = (Integer) this.save(user);
        user.setId(id);
        return user;
    }

    @Override
    public User getUserByAccount(String account) {
        String hql = "from User where account =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, account);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public List<User> search(String condition) {
        if(condition == null || condition.isEmpty()) {
            return Collections.emptyList();
        }else {
            if(!condition.startsWith("%")) {
                condition = "%" + condition;
            }

            if(!condition.endsWith("%")) {
                condition = condition + "%";
            }
        }
        String hql = "from User where account like ? or name like ?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, condition);
        query.setParameter(1, condition);
        return query.list();
    }

    @Override
    public User getUserById(int userId) {
        String hql = "from User where id =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return (User) query.uniqueResult();
    }
}
