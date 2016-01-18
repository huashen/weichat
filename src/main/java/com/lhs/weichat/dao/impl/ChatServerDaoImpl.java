package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IChatServerDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ChatServerDao
 *
 * @author longhuashen
 * @since 15/10/3
 */
//@Repository
public class ChatServerDaoImpl extends BaseDao implements IChatServerDao {

    @Override
    public List<ChatServer> getOnlineServer() {
        String hql = "from ChatServer";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public ChatServer getChatServerByIpAndPort(String ip, int port) {
        String hql = "from ChatServer where ip =? and port =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        return (ChatServer) query.uniqueResult();
    }

    @Override
    public void update(ChatServer server) {
        this.update(server);
    }

    @Override
    public void insert(ChatServer server) {
        this.insert(server);
    }
}
