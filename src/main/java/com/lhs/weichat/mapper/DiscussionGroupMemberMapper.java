package com.lhs.weichat.mapper;

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
}
