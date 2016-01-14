package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IFriendsGroupDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FriendsGroupDaoImpl
 *
 * @author longhuashen
 * @since 16/1/14
 */
@Repository
public class FriendsGroupDaoImpl extends BaseDao implements IFriendsGroupDao {

    @Override
    public List<FriendsGroup> getFriendsGroupByUserId(int id) {
        String hql = "from FriendsGroup where userId =? ordery by position ASC";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger(0, id);
        return query.list();
    }
}
