package com.lhs.weichat.service;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.bean.UserAuthToken;

import java.util.Set;

/**
 * IUserOnlineServerService
 *
 * @author longhuashen
 * @since 15/10/6
 */
public interface IUserOnlineServerService {

    /**
     * 从数据库查询用户所在位置
     *
     * @param userId
     * @param token
     * @return
     */
    ChatServer getOnlineServer(int userId, String token);

    /**
     * 获取一个用户所在的服务器
     *
     * @param userId
     * @return
     */
    Set<ChatServer> getOnlineServer(int userId);

    /**
     * 获取一个用户所有在线的授权token
     *
     * @param userId
     * @return
     */
    Set<UserAuthToken> getOnlineToken(int userId);

    /**
     * 设置用户登录的服务器
     *
     * @param userId
     * @param token
     * @param ip
     */
    void saveUserOnlineServer(int userId, String token, String ip, int port);

    /**
     * 取消在线状态
     *
     * @param userId
     * @param token
     * @param ip
     * @param port
     */
    void removeUserOnlineServer(int userId, String token, String ip, int port);
}
