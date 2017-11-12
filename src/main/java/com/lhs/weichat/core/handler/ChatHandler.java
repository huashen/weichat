package com.lhs.weichat.core.handler;

import com.lhs.weichat.bean.ChatGroupMember;
import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.bean.DiscussionGroupMember;
import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.ChatGroupMemberService;
import com.lhs.weichat.service.ChatMessageService;
import com.lhs.weichat.service.DiscussionGroupMemberService;
import com.lhs.weichat.service.FriendsService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ChatHandler
 *
 * @author longhuashen
 * @since 17/10/4
 */
@ChannelHandler.Sharable
@Component
public class ChatHandler extends SimpleChannelInboundHandler<ChatMessage> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ChatGroupMemberService chatGroupMemberService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private DiscussionGroupMemberService discussionGroupMemberService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) throws Exception {
        //客户端认证
        if (sessionManager.clientAuth(msg.getFromId() + "", msg.getToken()) != null) {
            if (msg.isTransfer()) {
                msg.setType(ChatMessage.TYPE_SEND);
//                msg = chatMessageService.addChatMessage(msg);
                Msg.Message message = MsgHelper.newReceiptMessage(msg.getUuid(), ChatMessage.STATUS_SEND);
                ctx.channel().writeAndFlush(message);
            }

            switch (msg.getMsgType()) {
                case ChatMessage.MSG_TYPE_UU:
                    Friends friends = friendsService.getFriendsByUserIdAndFriendsId(msg.getFromId(), msg.getToId());
                    if (friends != null) {
                        sessionManager.sendMessage(msg);
                    }
                    break;
                case ChatMessage.MSG_TYPE_UCG:
                    List<ChatGroupMember> unshieldMemberByChatGroupId = chatGroupMemberService.getUnshieldMemberByChatGroupId(msg.getChatGroupId());
                    if (unshieldMemberByChatGroupId != null) {
                        for (ChatGroupMember member : unshieldMemberByChatGroupId) {
                            if (msg.getFromId() != member.getUser().getId()) {
                                sessionManager.sendMessage(msg);
                            }
                        }
                    }
                    break;
                case ChatMessage.MSG_TYPE_UDG:
                    List<DiscussionGroupMember> unshieldMemberByDiscussionGroupId = discussionGroupMemberService.getUnshieldMemberByDiscussionGroupId(msg.getDiscussionGroupId());
                    if (unshieldMemberByDiscussionGroupId != null) {
                        for (DiscussionGroupMember discussionGroupMember : unshieldMemberByDiscussionGroupId) {
                            msg.setToId(discussionGroupMember.getUser().getId());
                            if (msg.getFromId() != discussionGroupMember.getUser().getId()) {
                                sessionManager.sendMessage(msg);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {
            Msg.Message rtMessage = MsgHelper.newResultMessage(Msg.MessageType.AUTH_ERROR, "用户认证失败，重新认证！");
            LOGGER.info("用户认证失败，重新认证！");
            ctx.channel().writeAndFlush(rtMessage);
        }
        ReferenceCountUtil.release(msg);
    }
}
