package com.lhs.weichat.dao;

import com.lhs.weichat.bean.FriendsGroup;

import java.util.List;

/**
 * IFriendsGroupDao
 *
 * @author longhuashen
 * @since 16/1/14
 */
public interface IFriendsGroupDao {

    List<FriendsGroup> getFriendsGroupByUserId(int id);
}
