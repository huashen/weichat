package com.lhs.weichat.dao;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;

import java.util.List;

/**
 * ChatGroupMemberDao
 *
 * @author longhuashen
 * @since 15/11/9
 */
public interface ChatGroupMemberDao {

    List<ChatGroup> getChatGroupByMember(int userId);

    List<ChatGroupMember> getChatGroupMemberByChatGroup(int chatGroupId);

    List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId);
}
