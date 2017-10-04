package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.ChatMessage;

import java.util.List;

/**
 * ChatMessageMapper
 *
 * @author longhuashen
 * @since 17/10/4
 */
public interface ChatMessageMapper {

    List<ChatMessage> getAllChatMessageByToId(int toId, int fromMessageId);

    ChatMessage addChatMessage(ChatMessage chatMessage);
}
