package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.FriendsDao;
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
    private FriendsDao friendsDao;

    @Override
    public List<Friends> getFriendsByUserId(int userId) {
        return friendsDao.getFriendsByUserId(userId);
    }

    @Override
    public void addFriend(User user, User friend) {
        if (isFriends(user, friend)) {
            return;
        }
        Friends friends = new Friends();
        friends.setUser(user);
        friends.setFriend(friend);
        friendsDao.saveFriend(friends);
    }

    @Override
    public boolean isFriends(User user, User friends) {
        return friendsDao.isFriends(user, friends);
    }

    @Override
    public void makeFriends(User user1, User user2) {
        Friends f = friendsDao.getFriendsByUserIdAndFriendsUserId(user1.getId(), user2.getId());

        if (f == null || f.getId() < 0) {
            addFriend(user1, user2);
        }

        Friends f2 = friendsDao.getFriendsByUserIdAndFriendsUserId(user2.getId(), user1.getId());
        if (f2 == null || f2.getId() < 0) {
            addFriend(user2, user1);
        }
    }

    @Override
    public Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId) {
        return friendsDao.getFriendsByUserIdAndFriendsUserId(userId, friendId);
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
}
