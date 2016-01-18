package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.dao.IFriendsGroupDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FriendsGroupDaoImpl
 *
 * @author longhuashen
 * @since 16/1/14
 */
@Repository
public class FriendsGroupDaoImpl extends GenericDaoImpl implements IFriendsGroupDao {

    @Override
    public List<FriendsGroup> getFriendsGroupByUserId(int id) {
        return null;
    }
}
