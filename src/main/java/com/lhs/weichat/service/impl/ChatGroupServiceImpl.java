package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.IChatGroupDao;
import com.lhs.weichat.service.IChatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChatGroupServiceImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
@Service
public class ChatGroupServiceImpl implements IChatGroupService {

    @Autowired
    private IChatGroupDao chatGroupDao;

    @Override
    public List<ChatGroup> search(String condition) {
        return chatGroupDao.search(condition);
    }

    @Override
    public ChatGroup addChatGroup(String name, User user) {
        return chatGroupDao.addChatGroup(name, user);
    }

    @Override
    public ChatGroup getChatGroupById(int chatGroupId) {
        return chatGroupDao.getChatGroupById(chatGroupId);
    }
}
