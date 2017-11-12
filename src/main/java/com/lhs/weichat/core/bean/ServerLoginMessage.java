package com.lhs.weichat.core.bean;

/**
 * ServerLoginMessage
 *
 * @author longhuashen
 * @since 17/11/12
 */
public class ServerLoginMessage {

    private String ip;

    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
