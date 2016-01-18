package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IChatGroupMemberDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ChatGroupMemberDaoImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
//@Repository
public class ChatGroupMemberDaoImpl extends BaseDao implements IChatGroupMemberDao {

    @Override
    public List<ChatGroup> getChatGroupByMember(int userId) {
        String hql = "from ChatGroupMember where userId =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        List<ChatGroupMember> chatGroupMembers = query.list();
        if (chatGroupMembers != null && chatGroupMembers.size() > 0) {
            ArrayList<ChatGroup> list = new ArrayList<>();
            for (ChatGroupMember chatGroupMember : chatGroupMembers) {
                list.add(chatGroupMember.getChatGroup());
            }
            return list;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ChatGroupMember> getChatGroupMemberByChatGroup(int chatGroupId) {
        String hql = "from ChatGroupMember where chatGroupId =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId) {
        String hql = "from ChatGroupMember where chatGroupId =? and shield =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, chatGroupId);
        query.setParameter(1, false);
        return query.list();
    }
}
