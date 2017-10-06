package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.User;

import java.util.List;

/**
 * ChatGroupMapper
 *
 * @author longhuashen
 * @since 15/11/9
 */
public interface ChatGroupMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ChatGroup record);

    int insertSelective(ChatGroup record);

    ChatGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatGroup record);

    int updateByPrimaryKey(ChatGroup record);

    List<ChatGroup> search(String condition);

    ChatGroup addChatGroup(String name, User user);

    ChatGroup getChatGroupById(int chatGroupId);
}
