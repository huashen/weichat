package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.User;

import java.util.List;

/**
 * FriendsMapper
 *
 * @author longhuashen
 * @since 15/11/5
 */
public interface FriendsMapper {

    List<Friends> getFriendsByUserId(int userId);

    boolean isFriends(User user, User friends);

    Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId);

    void saveFriend(Friends friends);
}
