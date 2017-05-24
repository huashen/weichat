package com.lhs.weichat.service;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.User;

import java.util.List;

/**
 * ChatGroupService
 *
 * @author longhuashen
 * @since 15/11/9
 */
public interface ChatGroupService {

    /**
     * 查找群组
     *
     * @param condition
     * @return
     */
    List<ChatGroup> search(String condition);

    /**
     * 创建群组
     *
     * @param name
     * @param user
     * @return
     */
    ChatGroup addChatGroup(String name, User user);

    /**
     * 根据id获取群组
     *
     * @param chatGroupId
     * @return
     */
    ChatGroup getChatGroupById(int chatGroupId);
}
