package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;

import java.util.List;

/**
 * ChatGroupMemberMapper
 *
 * @author longhuashen
 * @since 15/11/9
 */
public interface ChatGroupMemberMapper {

    List<ChatGroup> getChatGroupByMember(int userId);

    List<ChatGroupMember> getChatGroupMemberByChatGroup(int chatGroupId);

    List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId);
}
