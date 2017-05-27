package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.mapper.ChatGroupMemberMapper;
import com.lhs.weichat.service.ChatGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChatGroupMemberServiceImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
@Service("chatGroupMemberService")
public class ChatGroupMemberServiceImpl implements ChatGroupMemberService {

    @Autowired
    private ChatGroupMemberMapper chatGroupMemberMapper;

    @Override
    public List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId) {
        return chatGroupMemberMapper.getUnshieldMemberByChatGroupId(chatGroupId);
    }

    @Override
    public void addMember(int chatGroupId, User user) {

    }

    @Override
    public List<ChatGroup> getChatGroupByMember(int userId) {
        return null;
    }

    @Override
    public void deleteMemberByUserId(int chatGroupId, int memberId) {

    }

    @Override
    public List<ChatGroup> getMemberByChatGroupId(int chatGroupId) {
        return null;
    }
}
