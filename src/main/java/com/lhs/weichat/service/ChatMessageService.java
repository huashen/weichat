package com.lhs.weichat.service;

import com.lhs.weichat.bean.ChatMessage;

import java.util.List;

/**
 * ChatMessageService
 *
 * @author longhuashen
 * @since 17/10/4
 */
public interface ChatMessageService {

    /**
     * 获取消息
     *
     * @param toId
     * @param fromMessageId
     * @return
     */
    List<ChatMessage> getAllMessageByToId(int toId, int fromMessageId);

    /**
     * 添加消息
     *
     * @param chatMessage
     * @return
     */
    ChatMessage addChatMessage(ChatMessage chatMessage);
}
