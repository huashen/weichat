package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FriendsMapper
 *
 * @author longhuashen
 * @since 15/11/5
 */
public interface FriendsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);


    List<Friends> getFriendsByUserId(int userId);

    Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId);

    Friends getFriendsByUserIdAndFriendsId(@Param("userId") Integer userId, @Param("friendId") Integer friendId);
}
