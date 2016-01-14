package com.lhs.weichat.service;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.bean.User;

import java.util.List;

/**
 * IChatGroupMemberService
 *
 * @author longhuashen
 * @since 15/11/9
 */
public interface IChatGroupMemberService {

    /**
     * 得到群中没有屏蔽群消息的成员
     *
     * @param chatGroupId
     * @return
     */
    List<ChatGroupMember> getUnshieldMemberByChatGroupId(int chatGroupId);

    /**
     * 添加群成员
     *
     * @param chatGroupId
     * @param user
     */
    void addMember(int chatGroupId, User user);

    /**
     * 根据用户id获取群组
     *
     * @param userId
     * @return
     */
    List<ChatGroup> getChatGroupByMember(int userId);

    /**
     * 用户退出群组
     *
     * @param chatGroupId
     * @param memberId
     */
    void deleteMemberByUserId(int chatGroupId, int memberId);

    /**
     * 根据群组id获取群组
     *
     * @param chatGroupId
     * @return
     */
    List<ChatGroup> getMemberByChatGroupId(int chatGroupId);
}
