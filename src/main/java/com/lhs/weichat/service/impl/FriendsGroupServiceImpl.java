package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.dao.FriendsGroupDao;
import com.lhs.weichat.service.FriendsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FriendsGroupServiceImpl
 *
 * @author longhuashen
 * @since 16/1/14
 */
@Service
public class FriendsGroupServiceImpl implements FriendsGroupService {

    @Autowired
    private FriendsGroupDao friendsGroupDao;

    @Override
    public List<FriendsGroup> getFriendsGroupByUserId(int id) {
        return friendsGroupDao.getFriendsGroupByUserId(id);
    }
}
