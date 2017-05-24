package com.lhs.weichat.dao;

import com.lhs.weichat.bean.FriendsGroup;

import java.util.List;

/**
 * FriendsGroupDao
 *
 * @author longhuashen
 * @since 16/1/14
 */
public interface FriendsGroupDao {

    List<FriendsGroup> getFriendsGroupByUserId(int id);
}
