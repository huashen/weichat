package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.dao.IChatGroupMemberDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ChatGroupMemberDaoImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
@Repository
public class ChatGroupMemberDaoImpl extends GenericDaoImpl implements IChatGroupMemberDao {

    @Override
    public List<ChatGroup> getChatGroupByMember(int userId) {
        return null;
    }

    @Override
    public List<ChatGroupMember> getChatGroupMemberByChatGroup(int chatGroupId) {
        return null;
    }

    @Override
    public List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId) {
        return null;
    }
}
