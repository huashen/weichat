package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.bean.DiscussionGroup;
import com.lhs.weichat.bean.DiscussionGroupMember;
import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.mapper.ChatGroupMemberMapper;
import com.lhs.weichat.mapper.DiscussionGroupMemberMapper;
import com.lhs.weichat.mapper.FriendsMapper;
import com.lhs.weichat.mapper.UserMapper;
import com.lhs.weichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserServiceImpl
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private ChatGroupMemberMapper chatGroupMemberMapper;

    @Autowired
    private DiscussionGroupMemberMapper discussionGroupMemberMapper;

    @Override
    public User addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public List<User> search(String condition) {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public List<User> getRelateUser(int userId) {
        Map<Integer, User> um = new HashMap<Integer, User>();
        // 0.自己
        User u = userMapper.getUserById(userId);
        um.put(u.getId(), u);
        // 1，好友
        List<Friends> fl = friendsMapper.getFriendsByUserId(userId);
        if (fl != null && fl.size() > 0) {
            for (Friends f : fl) {
                um.put(f.getFriend().getId(), f.getFriend());
            }
        }
        // 2.群好友
        List<ChatGroup> cl = chatGroupMemberMapper.getChatGroupByMember(userId);
        if (cl != null && cl.size() > 0) {
            for (ChatGroup cg : cl) {
                List<ChatGroupMember> cgm = chatGroupMemberMapper
                        .getChatGroupMemberByChatGroup(cg.getId());
                if (cgm != null && cgm.size() > 0) {
                    for (ChatGroupMember m : cgm) {
                        um.put(m.getUser().getId(), m.getUser());
                    }
                }
            }
        }
        // 3.讨论组

        List<DiscussionGroup> dl = discussionGroupMemberMapper
                .getAllMyDiscussionGroup(userId);
        if (cl != null && cl.size() > 0) {
            for (DiscussionGroup cg : dl) {
                List<DiscussionGroupMember> cgm = discussionGroupMemberMapper
                        .getDiscussionGroupMemberByDiscussionGroup(cg.getId());
                if (cgm != null && cgm.size() > 0) {
                    for (DiscussionGroupMember m : cgm) {
                        System.out.println("d user id:" + m.getUser().getId());
                        um.put(m.getUser().getId(), m.getUser());
                    }
                }
            }
        }

        List data = new ArrayList<User>();
        data.addAll(um.values());
        return data;
    }
}
