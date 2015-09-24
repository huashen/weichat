package com.lhs.weichat.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import javax.annotation.Resource;

/**
 * BaseDao
 *
 * @author longhuashen
 * @since 15/9/24
 */
public class BaseDao extends HibernateTemplate {

    @Override
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void insert(Object entity) {
        this.save(entity);
    }

    public void update(Object entity) {
        super.update(entity);
    }
}
