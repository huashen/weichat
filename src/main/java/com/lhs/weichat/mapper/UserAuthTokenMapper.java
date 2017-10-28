package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.bean.UserOnlineServer;
import org.apache.ibatis.annotations.Param;

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


    UserAuthToken getUserAuthTokenByUserIdAndToken(@Param("userId") int userId, @Param("token") String token);

    List<UserAuthToken> getUserAuthTokenByUserId(int userId);

    UserAuthToken getUserAuthTokenByToken(String token);

    UserOnlineServer getOnlineServerByToken(int tokenId);
}
