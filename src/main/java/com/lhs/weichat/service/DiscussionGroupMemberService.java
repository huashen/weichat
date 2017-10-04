package com.lhs.weichat.service;

import com.lhs.weichat.bean.DiscussionGroup;
import com.lhs.weichat.bean.DiscussionGroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DiscussionGroupMemberServiceImpl
 *
 * @author longhuashen
 * @since 17/10/4
 */
@Service
public interface DiscussionGroupMemberService {

    List<DiscussionGroupMember> getUnshieldMemberByDiscussionGroupId(int discussionGroupId);

    List<DiscussionGroup> getDiscussionGroupByMemberId(int userId);
}
