package com.lhs.weichat.service;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.bean.User;

import java.util.List;

/**
 * FriendsService
 *
 * @author longhuashen
 * @since 15/11/5
 */
public interface FriendsService {

    /**
     * 通过userId查找好友
     *
     * @param userId
     * @return
     */
    List<Friends> getFriendsByUserId(int userId);

    /**
     * 添加好友
     *
     * @param user
     * @param friend
     */
    void addFriend(User user, User friend);

    /**
     * 判断是否好友
     *
     * @param user
     * @param friends
     * @return
     */
    boolean isFriends(User user, User friends);

    /**
     * 互加好友
     *
     * @param user1
     * @param user2
     */
    void makeFriends(User user1, User user2);

    /**
     * 得到用户指定好友
     *
     * @param userId
     * @param friendId
     * @return
     */
    Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId);

    /**
     * 获取好友的在线状态
     *
     * @param userId
     * @param FriendsId
     * @return
     */
    int getFriendsOnlineStatus(int userId, int FriendsId);

    /**
     * @param f
     * @param fg
     */
    void moveFriendsGroup(Friends f, FriendsGroup fg);

    Friends getFriendsByUserIdAndFriendsId(int userId, int friendsId);

    List<FriendsGroup> getFriendsGroupByUserId(int userId);
}
