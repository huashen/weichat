package com.lhs.weichat.dao;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.User;

import java.util.List;

/**
 * IChatGroupDao
 *
 * @author longhuashen
 * @since 15/11/9
 */
public interface IChatGroupDao {

    List<ChatGroup> search(String condition);

    ChatGroup addChatGroup(String name, User user);

    ChatGroup getChatGroupById(int chatGroupId);
}
