package com.lhs.weichat.core.bean;

/**
 * ClientLoginMessage
 *
 * @author longhuashen
 * @since 17/5/28
 */
public class ClientLoginMessage {

    private String token;

    private int userId;

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
