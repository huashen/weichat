package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.ChatGroupDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ChatGroupDaoImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
@Service
public class ChatGroupDaoImpl extends GenericDaoImpl implements ChatGroupDao {

    private int maxAccount;

    @Override
    public List<ChatGroup> search(String condition) {
        return null;
    }

    @Override
    public ChatGroup addChatGroup(String name, User user) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setCreateBy(user);
        chatGroup.setCreateDate(new Date());
        chatGroup.setAccount((getMaxAccount() + 1) + "");


        return null;
    }

    @Override
    public ChatGroup getChatGroupById(int chatGroupId) {
       return null;
    }

    public int getMaxAccount() {
        return 1;
    }
}
