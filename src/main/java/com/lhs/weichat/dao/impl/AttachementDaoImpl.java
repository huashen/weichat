package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Attachement;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IAttachementDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * AttachementDaoImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Repository
public class AttachementDaoImpl extends BaseDao implements IAttachementDao {

    public Attachement saveAttachment(Attachement attachement) {
        Integer id = (Integer) this.save(attachement);
        attachement.setId(id);
        return attachement;
    }

    public Attachement getAttachmentByGroupNameAndPath(String groupName, String path) {
        String hql = "from Attachment where groupName =? and path =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, groupName);
        query.setParameter(1, path);
        Attachement attachement = (Attachement) query.uniqueResult();
        return attachement;
    }

    public Attachement getAttachementById(int id) {
        String hql = "from Attachement where id = ?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        Attachement attachement = (Attachement) query.uniqueResult();
        return attachement;
    }
}
