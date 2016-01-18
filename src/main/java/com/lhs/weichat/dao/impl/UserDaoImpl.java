package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.IUserDao;
import org.springframework.stereotype.Repository;

/**
 * UserDaoImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl implements IUserDao {

    @Override
    public User addUser(User user) {
        this.insert("UserMapper.createUser", user);
        return user;
    }

    @Override
    public User getUserByAccount(String account) {
        return this.selectOne("UserMapper.getUserByAccount", account);
    }
//
//    @Override
//    public List<User> search(String condition) {
//        if(condition == null || condition.isEmpty()) {
//            return Collections.emptyList();
//        }else {
//            if(!condition.startsWith("%")) {
//                condition = "%" + condition;
//            }
//
//            if(!condition.endsWith("%")) {
//                condition = condition + "%";
//            }
//        }
//        String hql = "from User where account like ? or name like ?";
//        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
//        query.setParameter(0, condition);
//        query.setParameter(1, condition);
//        return query.list();
//    }

    @Override
    public User getUserById(int userId) {
          return this.selectOne("UserMapper.getUserById", userId);
    }
}
