package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.DiscussionGroup;
import com.lhs.weichat.bean.DiscussionGroupMember;

import java.util.List;

/**
 * DiscussionGroupMemberMapper
 *
 * @author longhuashen
 * @since 17/10/4
 */
public interface DiscussionGroupMemberMapper {

    List<DiscussionGroupMember> getUnshieldMemberByDiscussionGroupId(int discussionGroupId);

    List<DiscussionGroup> getDiscussionGroupByMemberId(int userId);

    List<DiscussionGroup> getAllMyDiscussionGroup(int userId);

    List<DiscussionGroupMember> getDiscussionGroupMemberByDiscussionGroup(Integer id);
}
