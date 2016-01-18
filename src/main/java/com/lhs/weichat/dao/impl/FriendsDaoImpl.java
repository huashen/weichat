package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.IFriendsDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FriendsDaoImpl
 *
 * @author longhuashen
 * @since 15/11/5
 */
@Repository
public class FriendsDaoImpl extends GenericDaoImpl implements IFriendsDao {

    @Override
    public List<Friends> getFriendsByUserId(int userId) {
        return null;
    }

    @Override
    public boolean isFriends(User user, User friends) {
        Friends f = getFriendsByUserIdAndFriendsUserId(user.getId(), friends.getId());
        if (f != null && f.getId() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId) {
        return null;
    }

    @Override
    public void saveFriend(Friends friends) {

    }
}
