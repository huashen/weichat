package com.lhs.weichat.core.handler;

import com.lhs.weichat.bean.ChatGroup;
import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.bean.DiscussionGroup;
import com.lhs.weichat.bean.Friends;
import com.lhs.weichat.bean.FriendsGroup;
import com.lhs.weichat.bean.Todo;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.ClientRequestMessage;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.ChatGroupMemberService;
import com.lhs.weichat.service.ChatMessageService;
import com.lhs.weichat.service.DiscussionGroupMemberService;
import com.lhs.weichat.service.FriendsService;
import com.lhs.weichat.service.TodoService;
import com.lhs.weichat.service.UserService;
import com.lhs.weichat.utils.StringUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClientRequestHandler
 * <p>
 * 1.保持服务器间的心跳 2.转发消息
 *
 * @author longhuashen
 * @since 17/10/4
 */
@ChannelHandler.Sharable
@Component
public class ClientRequestHandler extends SimpleChannelInboundHandler<ClientRequestMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRequestHandler.class);

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatGroupMemberService chatGroupMemberService;

    @Autowired
    private DiscussionGroupMemberService discussionGroupMemberService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClientRequestMessage msg) throws Exception {
        if (sessionManager.clientAuth(msg.getUserId() + "", msg.getToken()) != null) {
            String sessionKey = msg.getUserId() + msg.getToken();
            switch (msg.getRequestType()) {
                case ClientRequestMessage.FRIEND_LIST:
                    Msg.Message message = getFriendsList(msg);
                    sessionManager.sendMessage(message, sessionKey);
                    break;
                case ClientRequestMessage.FRIEND_GROUP_LIST:
                    List<FriendsGroup> friendsGroupList = friendsService.getFriendsGroupByUserId(msg
                            .getUserId());
                    Msg.Message mfg = MsgHelper.newFriendsGroupListMessage(friendsGroupList);
                    sessionManager.sendMessage(mfg, sessionKey);
                    break;
                case ClientRequestMessage.CHAT_MESSAGE_LIST:
                    String parameter = msg.getParameter();
                    int fromMsgId = StringUtils.toInt(parameter);

                    List<ChatMessage> chatMessageList = chatMessageService.getAllMessageByToId(msg.getUserId(), fromMsgId);

                    if (chatMessageList != null) {
                        for (ChatMessage chatMessage : chatMessageList) {
                            //重置token
                            chatMessage.setToken("");
                        }
                    }
                    Msg.Message cmg = MsgHelper.newChatMessageListMessage(chatMessageList);
                    sessionManager.sendMessage(cmg, sessionKey);
                    break;
                case ClientRequestMessage.TODO_LIST:
                    String parameter1 = msg.getParameter();
                    int fid = StringUtils.toInt(parameter1);

                    List<Todo> todoList = todoService.getAllTodoByToId(msg.getUserId(), fid);
                    Msg.Message tmg = MsgHelper.newTodoListMessage(todoList);
                    sessionManager.sendMessage(tmg, sessionKey);
                    break;
                case ClientRequestMessage.CHAT_GROUP_LIST:
                    List<ChatGroup> chatGroupByMember = chatGroupMemberService.getChatGroupByMember(msg.getUserId());
                    Msg.Message tcg = MsgHelper.newChatGroupListMessage(chatGroupByMember);
                    sessionManager.sendMessage(tcg, sessionKey);
                    break;
                case ClientRequestMessage.CHAT_GROUP_MEMBER_LIST:
                    List<DiscussionGroup> discussionGroupList = discussionGroupMemberService.getDiscussionGroupByMemberId(msg.getUserId());
                    Msg.Message tdg = MsgHelper.newDiscussionGroupListMessage(discussionGroupList);
                    sessionManager.sendMessage(tdg, sessionKey);
                    break;
                case ClientRequestMessage.DISCUSSION_GROUP_LIST:
                    break;
                case ClientRequestMessage.DISCUSSION_GROUP_MEMBER_LIST:
                    break;
                case ClientRequestMessage.RELATE_USER_LIST:
                    List<User> relateUser = userService.getRelateUser(msg.getUserId());
                    Msg.Message ul = MsgHelper.newUserListMessage(relateUser);
                    sessionManager.sendMessage(ul, sessionKey);
                    break;
                default:
                    break;
            }
        }
    }

    private Msg.Message getFriendsList(ClientRequestMessage msg) {
        //获取用户的好友列表
        List<Friends> friendsList = friendsService.getFriendsByUserId(msg.getUserId());
        for (Friends friends : friendsList) {
            friends.setOnlineStatus(friendsService.getFriendsOnlineStatus(msg.getUserId(), friends.getId()));
        }
        return MsgHelper.newFriendsListMessage(friendsList);
    }
}
