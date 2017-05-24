package com.lhs.weichat.service;

import com.lhs.weichat.bean.FriendsGroup;

import java.util.List;

/**
 * FriendsGroupService
 *
 * @author longhuashen
 * @since 16/1/14
 */
public interface FriendsGroupService {

    List<FriendsGroup> getFriendsGroupByUserId(int id);
}
