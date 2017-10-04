package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.DiscussionGroupMember;
import com.lhs.weichat.mapper.DiscussionGroupMemberMapper;
import com.lhs.weichat.service.DiscussionGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DiscussionGroupMemberServiceImpl
 *
 * @author longhuashen
 * @since 17/10/4
 */
@Service
public class DiscussionGroupMemberServiceImpl implements DiscussionGroupMemberService {

    @Autowired
    private DiscussionGroupMemberMapper discussionGroupMemberMapper;

    @Override
    public List<DiscussionGroupMember> getUnshieldMemberByDiscussionGroupId(int discussionGroupId) {
        return discussionGroupMemberMapper.getUnshieldMemberByDiscussionGroupId(discussionGroupId);
    }
}
