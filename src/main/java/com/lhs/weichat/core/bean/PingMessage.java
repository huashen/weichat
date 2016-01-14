package com.lhs.weichat.core.bean;

/**
 * PingMessage
 *
 * @author longhuashen
 * @since 15/10/9
 */
public class PingMessage {

    private Msg.MessageType messageType;

    private String clientId;

    public Msg.MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(Msg.MessageType messageType) {
        this.messageType = messageType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
