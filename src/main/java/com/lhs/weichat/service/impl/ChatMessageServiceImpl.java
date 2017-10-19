package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.bean.DiscussionGroup;
import com.lhs.weichat.mapper.ChatMessageMapper;
import com.lhs.weichat.mapper.DiscussionGroupMemberMapper;
import com.lhs.weichat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private DiscussionGroupMemberMapper discussionGroupMemberMapper;

    @Override
    public List<ChatMessage> getAllMessageByToId(int toId, int fromMessageId) {
        List<Integer> arrayList = new ArrayList();
        arrayList.add(-111);
        List<DiscussionGroup> discussionGroupList = discussionGroupMemberMapper.getAllMyDiscussionGroup(toId);
        for (DiscussionGroup discussionGroup : discussionGroupList) {
            arrayList.add(discussionGroup.getId());
        }
        return chatMessageMapper.getAllChatMessageByToId(toId, fromMessageId, arrayList);
    }

    @Override
    public ChatMessage addChatMessage(ChatMessage chatMessage) {
        int num = chatMessageMapper.insertSelective(chatMessage);
        if (num < 0) {
            return null;
        }
        return chatMessage;
    }
}
