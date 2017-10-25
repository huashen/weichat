package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.bean.UserOnlineServer;
import com.lhs.weichat.mapper.FriendsMapper;
import com.lhs.weichat.mapper.UserAuthTokenMapper;
import com.lhs.weichat.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FriendsServiceImpl
 *
 * @author longhuashen
 * @since 15/11/5
 */
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private UserAuthTokenMapper userAuthTokenMapper;

    @Override
    public List<Friends> getFriendsByUserId(int userId) {
        return friendsMapper.getFriendsByUserId(userId);
    }

    @Override
    public void addFriend(User user, User friend) {
        if (isFriends(user.getId(), friend.getId())) {
            return;
        }
        Friends friends = new Friends();
        friends.setUser(user);
        friends.setFriend(friend);
        friendsMapper.insertSelective(friends);
    }

    @Override
    public boolean isFriends(Integer userId, Integer friendId) {
        Friends f = friendsMapper.getFriendsByUserIdAndFriendsUserId(userId, friendId);
        if (f != null && f.getId() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void makeFriends(User user1, User user2) {
        Friends f = friendsMapper.getFriendsByUserIdAndFriendsUserId(user1.getId(), user2.getId());

        if (f == null || f.getId() < 0) {
            addFriend(user1, user2);
        }

        Friends f2 = friendsMapper.getFriendsByUserIdAndFriendsUserId(user2.getId(), user1.getId());
        if (f2 == null || f2.getId() < 0) {
            addFriend(user2, user1);
        }
    }

    @Override
    public Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId) {
        return friendsMapper.getFriendsByUserIdAndFriendsUserId(userId, friendId);
    }

    @Override
    public int getFriendsOnlineStatus(int userId, int friendsId) {
        int onlineStatus = UserOnlineServer.ONLINE_STATUS_OFFLINE;
        // 1、是否被隐身
        Friends f = friendsMapper.getFriendsByUserIdAndFriendsId(userId, friendsId);
        if (f == null) {
            // 此为异常情况
            return UserOnlineServer.ONLINE_STATUS_OFFLINE;
        }
        if (f.isShield()) {
            return UserOnlineServer.ONLINE_STATUS_OFFLINE;
        } else {
            // 先获取用户的授权token，然后去查看
            List<UserAuthToken> list = userAuthTokenMapper.getUserAuthTokenByUserId(friendsId);
            if (list != null) {
                for (UserAuthToken t : list) {
                    UserOnlineServer us = userAuthTokenMapper
                            .getOnlineServerByToken(t.getId());
                    if (us != null && onlineStatus > us.getOnlineStatus()) {
                        // 获取最小的
                        onlineStatus = us.getOnlineStatus();
                    }
                }
            }
        }
        return onlineStatus;
    }

    @Override
    public void moveFriendsGroup(Friends f, FriendsGroup fg) {

    }

    @Override
    public Friends getFriendsByUserIdAndFriendsId(int userId, int friendsId) {
        return null;
    }

    @Override
    public List<FriendsGroup> getFriendsGroupByUserId(int userId) {
        return null;
    }
}
