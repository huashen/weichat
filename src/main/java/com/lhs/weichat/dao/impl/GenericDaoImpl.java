package com.lhs.weichat.dao.impl;

import com.lhs.weichat.dao.GenericDao;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import java.util.List;

/**
 * GenericDaoImpl
 *
 * @author longhuashen
 * @since 16/1/15
 */
public class GenericDaoImpl implements GenericDao {

    private SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    @Resource(name = "sqlSession")
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Long insert(String paramString, Object paramObject) {
        return Long.valueOf(getSqlSession().insert(paramString, paramObject));
    }

    @Override
    public Long delete(String paramString, Object paramObject) {
        return Long.valueOf(getSqlSession().delete(paramString, paramObject));
    }

    @Override
    public Long update(String paramString, Object paramObject) {
        return Long.valueOf(getSqlSession().update(paramString, paramObject));
    }

    @Override
    public <T> T selectOne(String paramString, Object paramObject) {
        Object selectOne = null;
        List<Object> list = selectList(paramString, paramObject);
        if (list != null && list.size() > 0) {
            selectOne = list.get(0);
        }
        return (T) selectOne;
    }

    @Override
    public <T> List<T> selectList(String paramString, Object paramObject) {
        return getSqlSession().selectList(paramString, paramObject);
    }
}
