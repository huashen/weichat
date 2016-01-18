package com.lhs.weichat.dao;

import java.util.List;

/**
 * IGenericDao
 *
 * @author longhuashen
 * @since 16/1/15
 */
public interface IGenericDao {

    /**
     * 添加记录
     *
     * @param paramString
     * @param paramObject
     * @return
     */
    Long insert(String paramString, Object paramObject);

    /**
     * 删除记录
     *
     * @param paramString
     * @param paramObject
     * @return
     */
    Long delete(String paramString, Object paramObject);

    /**
     * 更新记录
     *
     * @param paramString
     * @param paramObject
     * @return
     */
    Long update(String paramString, Object paramObject);

    /**
     * 获取单条记录
     *
     * @param paramString
     * @param paramObject
     * @param <T>
     * @return
     */
    <T> T selectOne(String paramString, Object paramObject);

    /**
     * 查询记录列表
     *
     * @param paramString
     * @param paramObject
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String paramString, Object paramObject);
}
