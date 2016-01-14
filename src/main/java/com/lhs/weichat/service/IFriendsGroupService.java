package com.lhs.weichat.service;

import com.lhs.weichat.bean.FriendsGroup;

import java.util.List;

/**
 * IFriendsGroupService
 *
 * @author longhuashen
 * @since 16/1/14
 */
public interface IFriendsGroupService {

    List<FriendsGroup> getFriendsGroupByUserId(int id);
}
