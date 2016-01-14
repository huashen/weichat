package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.IChatGroupMemberDao;
import com.lhs.weichat.service.IChatGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChatGroupMemberServiceImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
@Service
public class ChatGroupMemberServiceImpl implements IChatGroupMemberService {

    @Autowired
    private IChatGroupMemberDao chatGroupMemberDao;

    @Override
    public List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId) {
        return chatGroupMemberDao.getUnshieldMemberByChatGroupId(chatGroupId);
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
