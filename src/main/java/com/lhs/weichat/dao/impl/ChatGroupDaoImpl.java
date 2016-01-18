package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IChatGroupDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ChatGroupDaoImpl
 *
 * @author longhuashen
 * @since 15/11/9
 */
//@Service
public class ChatGroupDaoImpl extends BaseDao implements IChatGroupDao {

    private int maxAccount;

    @Override
    public List<ChatGroup> search(String condition) {
        if (condition == null || condition.isEmpty()) {
            return null;
        } else {
            if (!condition.startsWith("%")) {
                condition = "%" + condition;
            }

            if (!condition.endsWith("%")) {
                condition = condition + "%";
            }
        }

        String hql = "from ChatGroup where account like ? or name like ?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, condition);
        query.setParameter(1, condition);
        return query.list();
    }

    @Override
    public ChatGroup addChatGroup(String name, User user) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setCreateBy(user);
        chatGroup.setCreateDate(new Date());
        chatGroup.setAccount((getMaxAccount() + 1) + "");

        Integer id = (Integer) save(chatGroup);
        chatGroup.setId(id);
        return chatGroup;
    }

    @Override
    public ChatGroup getChatGroupById(int chatGroupId) {
        String hql = "from ChatGroup where id =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, chatGroupId);
        return (ChatGroup) query.uniqueResult();
    }

    public int getMaxAccount() {
        int maxAccount = 100000;
        String hql = "from ChatGroup order by id desc";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setMaxResults(1);
        ChatGroup chatGroup = (ChatGroup) query.uniqueResult();
        if (chatGroup != null) {
            maxAccount = Integer.parseInt(chatGroup.getAccount());
        }
        return maxAccount;
    }
}
