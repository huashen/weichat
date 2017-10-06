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

    int deleteByPrimaryKey(Integer id);

    int insert(ChatGroupMember record);

    int insertSelective(ChatGroupMember record);

    ChatGroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatGroupMember record);

    int updateByPrimaryKey(ChatGroupMember record);


    List<ChatGroup> getChatGroupByMember(int userId);

    List<ChatGroupMember> getChatGroupMemberByChatGroup(int chatGroupId);

    List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId);
}
