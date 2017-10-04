package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.mapper.FriendsMapper;
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

    @Override
    public List<Friends> getFriendsByUserId(int userId) {
        return friendsMapper.getFriendsByUserId(userId);
    }

    @Override
    public void addFriend(User user, User friend) {
        if (isFriends(user, friend)) {
            return;
        }
        Friends friends = new Friends();
        friends.setUser(user);
        friends.setFriend(friend);
        friendsMapper.saveFriend(friends);
    }

    @Override
    public boolean isFriends(User user, User friends) {
        return friendsMapper.isFriends(user, friends);
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
    public int getFriendsOnlineStatus(int userId, int FriendsId) {
        return 0;
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
