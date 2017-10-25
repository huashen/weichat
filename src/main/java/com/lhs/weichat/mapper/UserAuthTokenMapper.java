package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.bean.UserOnlineServer;

import java.util.List;

/**
 * UserAuthTokenMapper
 *
 * @author longhuashen
 * @since 15/9/28
 */
public interface UserAuthTokenMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserAuthToken record);

    int insertSelective(UserAuthToken record);

    UserAuthToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuthToken record);

    int updateByPrimaryKey(UserAuthToken record);


    UserAuthToken getUserAuthTokenByUserIdAndToken(int userId, String token);

    List<UserAuthToken> getUserAuthTokenByUserId(int userId);

    UserAuthToken getUserAuthTokenByToken(String token);

    void saveUserAuthToken(UserAuthToken userAuthToken);

    UserOnlineServer getOnlineServerByToken(int tokenId);
}
