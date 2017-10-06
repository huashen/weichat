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

    int deleteByPrimaryKey(Integer id);

    int insert(ChatMessage record);

    int insertSelective(ChatMessage record);

    ChatMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatMessage record);

    int updateByPrimaryKey(ChatMessage record);


    List<ChatMessage> getAllChatMessageByToId(int toId, int fromMessageId);

    ChatMessage addChatMessage(ChatMessage chatMessage);
}
