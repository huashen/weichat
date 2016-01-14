package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.IFriendsDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FriendsDaoImpl
 *
 * @author longhuashen
 * @since 15/11/5
 */
@Repository
public class FriendsDaoImpl extends BaseDao implements IFriendsDao {

    @Override
    public List<Friends> getFriendsByUserId(int userId) {
        String hql = "from Friends where userId =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger(0, userId);

        return query.list();
    }

    @Override
    public boolean isFriends(User user, User friends) {
        Friends f = getFriendsByUserIdAndFriendsUserId(user.getId(), friends.getId());
        if (f != null && f.getId() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Friends getFriendsByUserIdAndFriendsUserId(int userId, int friendId) {
        String hql = "from Friends where userId =? and friendsId =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger(0, userId);
        query.setInteger(1, friendId);
        return (Friends) query.uniqueResult();
    }

    @Override
    public void saveFriend(Friends friends) {
        this.save(friends);
    }
}
