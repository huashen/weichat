package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IAttachmentDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * AttachmentDaoImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Repository
public class AttachmentDaoImpl extends BaseDao implements IAttachmentDao {

    public Attachment saveAttachment(Attachment Attachment) {
        Integer id = (Integer) this.save(Attachment);
        Attachment.setId(id);
        return Attachment;
    }

    public Attachment getAttachmentByGroupNameAndPath(String groupName, String path) {
        String hql = "from Attachment where groupName =? and path =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, groupName);
        query.setParameter(1, path);
        Attachment Attachment = (Attachment) query.uniqueResult();
        return Attachment;
    }

    public Attachment getAttachmentById(int id) {
        String hql = "from Attachment where id = ?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        Attachment Attachment = (Attachment) query.uniqueResult();
        return Attachment;
    }
}
