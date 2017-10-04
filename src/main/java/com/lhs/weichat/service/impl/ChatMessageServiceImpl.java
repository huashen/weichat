package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.mapper.ChatMessageMapper;
import com.lhs.weichat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChatMessageServiceImpl
 *
 * @author longhuashen
 * @since 17/10/4
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Override
    public List<ChatMessage> getAllMessageByToId(int toId, int fromMessageId) {
        return chatMessageMapper.getAllChatMessageByToId(toId, fromMessageId);
    }

    @Override
    public ChatMessage addChatMessage(ChatMessage chatMessage) {
        return chatMessageMapper.addChatMessage(chatMessage);
    }
}
