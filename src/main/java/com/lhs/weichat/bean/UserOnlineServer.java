package com.lhs.weichat.bean;

import java.io.Serializable;

public class UserOnlineServer implements Serializable {

    public final static int ONLINE_STATUS_WANNA_TALK = 1;// 求聊
    public final static int ONLINE_STATUS_ONLINE = 2;// 在线
    public final static int ONLINE_STATUS_BUSY = 3;// 忙碌
    public final static int ONLINE_STATUS_NO_DISTURB = 4;// 勿扰
    public final static int ONLINE_STATUS_LEAVE = 5;// 离开
    public final static int ONLINE_STATUS_INVISIBLE = 6;// 隐身
    public final static int ONLINE_STATUS_OFFLINE = 7;// 离线

    private Integer id;

    private Integer onlineStatus;

    private Integer chatServerId;

    private Integer userAuthTokenId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getChatServerId() {
        return chatServerId;
    }

    public void setChatServerId(Integer chatServerId) {
        this.chatServerId = chatServerId;
    }

    public Integer getUserAuthTokenId() {
        return userAuthTokenId;
    }

    public void setUserAuthTokenId(Integer userAuthTokenId) {
        this.userAuthTokenId = userAuthTokenId;
    }
}