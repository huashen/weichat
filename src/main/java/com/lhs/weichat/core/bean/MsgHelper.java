package com.lhs.weichat.core.bean;

import com.lhs.weichat.bean.*;
import com.lhs.weichat.utils.StringUtils;

import java.util.List;

/**
 * MsgHelper
 *
 * @author longhuashen
 * @since 15/10/8
 */
public class MsgHelper {

    /**
     * 生成一个 带返回消息的消息
     *
     * @param type
     * @param message
     * @return
     */
    public static Msg.Message newResultMessage(Msg.MessageType type, String message) {
        Msg.ResultMessage.Builder builder = Msg.ResultMessage.newBuilder();
        Msg.ResultMessage rtMessage = builder.setMessage(message)
                .setMessageType(type).build();
        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setResultMessage(rtMessage).setMessageType(type)
                .build();
        return m;
    }

    /**
     * 生成一个带ping/pong消息的消息
     *
     * @param clientId
     * @return
     */
    public static Msg.Message newPingMessage(Msg.MessageType type, String clientId) {
        Msg.PingMessage.Builder bu = Msg.PingMessage.newBuilder();
        Msg.PingMessage rtMessage = bu.setClientId(clientId).setMessageType(type).build();
        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setPingMessage(rtMessage).setMessageType(type).build();
        return m;
    }

    /**
     * 聊天消息
     *
     * @param fromId
     * @param toId
     * @param content
     * @param token
     * @param transfer
     * @param date
     * @param id
     * @param contentType
     * @param fileGroupName
     * @param path
     * @param chatGroupId
     * @param discussionGroupId
     * @param msgType
     * @param uuid
     * @param status
     * @return
     */
    public static Msg.Message newChatMessage(int fromId, int toId, String content, String token, boolean transfer, String date
            , int id, int contentType, String fileGroupName, String path, int chatGroupId, int discussionGroupId
            , int msgType, String uuid, int status) {

        Msg.ChatMessage chatMessage = Msg.ChatMessage.newBuilder()
                .setContent(content)
                .setFromId(fromId)
                .setToId(toId)
                .setMsgType(ChatMessage.MSG_TYPE_UU)
                .setToken(token)
                .setChatMessageId(id)
                .setDate(date)
                .setTransfer(transfer)
                .setFileGroupName(fileGroupName)
                .setFilePath(path)
                .setChatGroupId(chatGroupId)
                .setUuid(uuid)
                .setStatus(status)
                .setDiscussionGroupId(discussionGroupId)
                .setMsgType(msgType)
                .setContentType(contentType)
                .build();
        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setChatMessage(chatMessage).setMessageType(Msg.MessageType.CHAT_MESSAGE).build();
        return m;
    }

    public static Msg.Message newChatMessage(ChatMessage ms) {
        Attachment attachment = ms.getAttachment();
        String path = "";
        String fileGroupName = "";
        if (attachment != null) {
            fileGroupName = attachment.getGroupName();
            path = attachment.getPath();
        }

        Msg.ChatMessage chatMessage = Msg.ChatMessage.newBuilder()
                .setContent(ms.getContent())
                .setFromId(ms.getFromId())
                .setToId(ms.getToId())
                .setMsgType(ms.getMsgType())
                .setDate(ms.getDate().getTime() + "")
                .setChatMessageId(ms.getId())
                .setChatGroupId(ms.getChatGroupId())
                .setMsgType(ms.getMsgType())
                .setUuid(ms.getUuid())
                .setStatus(ms.getStatus())
                .setDiscussionGroupId(ms.getDiscussionGroupId())
                .setFileGroupName(fileGroupName)
                .setFilePath(path)
                .setContentType(ms.getContentType())
                .setTransfer(false)
                .build();
        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setChatMessage(chatMessage).setMessageType(Msg.MessageType.CHAT_MESSAGE).build();
        return m;
    }

    public static Msg.Message newFileDownloadMessage(Attachment file) {
        Msg.FileDownload.Builder builder = Msg.FileDownload.newBuilder();
        Msg.FileDownload f = builder.setGroupName(file.getGroupName())
                .setGroupName(file.getGroupName())
                .setName(file.getName())
                .setPath(file.getPath())
                .setSize(file.getSize())
                .setType(file.getType())
                .setFileId(file.getId())
                .build();
        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setFileDownload(f).setMessageType(Msg.MessageType.FIEL).build();
        return m;
    }

    /**
     * 使用json包装数据
     *
     * @param type
     * @param jsonStr
     * @return
     */
    public static Msg.Message newJsonMessage(int type, String jsonStr) {
        Msg.JsonMessage jsonMessage = Msg.JsonMessage.newBuilder().setJsonMessageType(type).setJsonStr(jsonStr).build();

        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setMessageType(Msg.MessageType.JSON_DATA).setJsonMessage(jsonMessage).build();
        return m;
    }

    /**
     * 新建代办事项
     *
     * @param id
     * @param subject
     * @param type
     * @param requestMsg
     * @return
     */
    public static Msg.Message newTodoMessage(int id, String subject, int type, String requestMsg) {
        Msg.TodoMessage todoMessage = Msg.TodoMessage.newBuilder().setTodoId(id).setTodoSubject(subject).setType(type).setRequestMsg(requestMsg).build();
        Msg.Message.Builder builder = Msg.Message.newBuilder();
        Msg.Message message = builder.setTodoMessage(todoMessage).setMessageType(Msg.MessageType.TODO).build();
        return message;
    }

    /**
     * 获取好友信息
     *
     * @param friends
     * @return
     */
    public static Msg.Message newFriendsListMessage(List<Friends> friends) {
        Msg.Message.Builder b = Msg.Message.newBuilder();
        if (friends != null) {
            int index = 0;
            for (Friends f : friends) {
                Msg.Friends.Builder fb = Msg.Friends.newBuilder();

                fb.setAge(StringUtils.getAge(f.getFriend().getBirthday()));
//                if (f.getFriend().getAvatar() != null) {
//                    fb.setAvatarPath(f.getFriend().getAvatar().getGroupName() + "#" + f.getFriend().getAvatar().getPath());
//                } else {
//                    fb.setAvatarPath("");
//                }
//                fb.setId(f.getId());
//                String name = f.getFriend().getName();

//                fb.setName(name);
//                if (f.getRemarkName() == null || f.getRemarkName().isEmpty()) {
//                    fb.setRemarkName(name);
//                } else {
//                    fb.setRemarkName(f.getRemarkName());
//                }

                boolean online = true;
                if (f.getOnlineStatus() < UserOnlineServer.ONLINE_STATUS_INVISIBLE) {
                    online = true;
                }

                fb.setOnline(online);
                fb.setOnlineType(f.getOnlineType());
                if (f.getFriend().getSignature() == null || f.getFriend().getSignature().isEmpty()) {
                    fb.setSignature("好好学习，天天向上！");
                } else {
                    fb.setSignature(f.getFriend().getSignature());
                }
                fb.setUserId(f.getFriend().getId());
                if (f.getFriendsGroup() != null) {
                    fb.setFriendsGroupId(f.getFriendsGroup().getId());
                } else {
                    fb.setFriendsGroupId(0);
                }
                Msg.Friends fm = fb.build();
                b.addFriendsListMessage(index, fb.build());
                index++;
            }
        }

        b.setMessageType(Msg.MessageType.FRIENDS_LIST);
        Msg.Message m = b.build();
        return m;
    }

    /**
     * 获取好友分组
     *
     * @param friendsGroup
     * @return
     */
    public static Msg.Message newFriendsGroupListMessage(List<FriendsGroup> friendsGroup) {
        Msg.Message.Builder b = Msg.Message.newBuilder();
        if (friendsGroup != null) {
            int index = 0;
            for (FriendsGroup fg : friendsGroup) {
                Msg.FriendsGroup.Builder gb = Msg.FriendsGroup.newBuilder();

                gb.setId(fg.getId());
                gb.setName(fg.getName());
                gb.setPosition(fg.getPosition());

                Msg.FriendsGroup g = gb.build();
                b.addFriendsGroupListMessage(index, g);
                index++;
            }
        }

        b.setMessageType(Msg.MessageType.FRIENDS_GROUP_LIST);
        Msg.Message m = b.build();
        return m;
    }

    /**
     * 聊天消息
     *
     * @param fromId
     * @param toId
     * @param content
     * @param token
     * @param date
     * @param contentType
     * @return
     */
    public static Msg.Message newUUChatMessage(String uuid, int fromId, int toId,
                                               String content, String token, boolean transfer, String date,
                                               int id, int contentType, String fileGroupName, String path,
                                               int status) {
        Msg.ChatMessage chatMessage = Msg.ChatMessage.newBuilder()
                .setContent(content).setFromId(fromId).setToId(toId)
                .setMsgType(ChatMessage.MSG_TYPE_UU).setToken(token)
                .setChatMessageId(id).setDate(date).setTransfer(transfer)
                .setFileGroupName(fileGroupName).setFilePath(path)
                .setStatus(status).setUuid(uuid).setContentType(contentType)
                .build();

        Msg.Message.Builder b = Msg.Message.newBuilder();
        Msg.Message m = b.setChatMessage(chatMessage)
                .setMessageType(Msg.MessageType.CHAT_MESSAGE).build();
        return m;
    }

    public static Msg.Message newReceiptMessage(String uuid, int status) {
        Msg.Message.Builder b = Msg.Message.newBuilder();

        Msg.ReceiptMessage.Builder rb = Msg.ReceiptMessage.newBuilder();
        rb.setStatus(status).setUuid(uuid);

        Msg.ReceiptMessage rm = rb.build();

        b.setMessageType(Msg.MessageType.RECEIPT);
        b.setReceiptMessage(rm);
        Msg.Message m = b.build();
        return m;
    }


    public static Msg.Message newTodoListMessage(List<Todo> todos) {
        return null;
    }

    public static Msg.Message newChatMessageListMessage(List<ChatMessage> messages) {
        Msg.Message.Builder b = Msg.Message.newBuilder();
        if (messages != null) {

            for (ChatMessage ms : messages) {
                Attachment a = ms.getAttachment();
                String path = "";
                String fileGroupName = "";
                if (a != null) {
                    fileGroupName = a.getGroupName();
                    path = a.getPath();
                }
                Msg.ChatMessage chatMessage = Msg.ChatMessage.newBuilder()
                        .setContent(ms.getContent()).setFromId(ms.getFromId())
                        .setToId(ms.getToId()).setMsgType(ms.getMsgType())
                        .setDate(ms.getDate().getTime() + "")
                        .setChatMessageId(ms.getId()).setToken(ms.getToken())
                        .setFileGroupName(fileGroupName).setFilePath(path)
                        .setContentType(ms.getContentType()).setTransfer(false)
                        .setChatGroupId(ms.getChatGroupId())
                        .setUuid(ms.getUuid()).setStatus(ms.getStatus())
                        .setDiscussionGroupId(ms.getDiscussionGroupId())
                        .setMsgType(ms.getMsgType()).build();
                b.addChatMessageListMessage(chatMessage);
            }
        }
        b.setMessageType(Msg.MessageType.CHAT_MESSAGE_LIST);
        Msg.Message m = b.build();
        return m;
    }

    /**
     * 聊天群
     *
     * @param chatGroup
     * @return
     */
    public static Msg.Message newChatGroupListMessage(List<ChatGroup> chatGroup) {
        Msg.Message.Builder b = Msg.Message.newBuilder();
        if (chatGroup != null) {
            int index = 0;
            for (ChatGroup fg : chatGroup) {
                Msg.ChatGroup.Builder gb = Msg.ChatGroup.newBuilder();

                gb.setId(fg.getId());
                gb.setName(fg.getName());
                gb.setSlogan(fg.getSlogan() + "");
                Msg.ChatGroup g = gb.build();
                b.addChatGroupListMessage(index, g);
                index++;
            }
        }
        b.setMessageType(Msg.MessageType.CHAT_GROUP_LIST);
        Msg.Message m = b.build();
        return m;
    }

    /**
     * 讨论组
     *
     * @return
     */
    public static Msg.Message newDiscussionGroupListMessage(
            List<DiscussionGroup> dGroup) {
        Msg.Message.Builder b = Msg.Message.newBuilder();
        if (dGroup != null) {
            int index = 0;
            for (DiscussionGroup fg : dGroup) {
                Msg.DiscussionGroup.Builder gb = Msg.DiscussionGroup
                        .newBuilder();
                gb.setId(fg.getId());
                gb.setName(fg.getName());
                Msg.DiscussionGroup g = gb.build();
                b.addDiscussionGroupListMessage(index, g);
                index++;
            }
        }
        b.setMessageType(Msg.MessageType.DISCUSSION_GROUP_LIST);
        Msg.Message m = b.build();
        return m;
    }

    public static Msg.Message newUserListMessage(List<User> relateUser) {
        Msg.Message.Builder b = Msg.Message.newBuilder();
        if (relateUser != null) {
            int index = 0;
            for (User u : relateUser) {
                Msg.User.Builder gb = Msg.User.newBuilder();
                gb.setId(u.getId());
                gb.setName(u.getNickName());
                gb.setAccount(u.getAccount());
//                if (u.getAvatar() != null) {
//                    gb.setAvatarPath(u.getAvatar().getGroupName() + "#"
//                            + u.getAvatar().getPath());
//                } else {
//                    gb.setAvatarPath("");
//                }
                gb.setBirthday(StringUtils.getDateString(u.getBirthday()));
                gb.setGender(u.getGender());
                gb.setSignature(u.getSignature() + "");
                Msg.User g = gb.build();
                b.addUserListMessage(index, g);
                index++;
            }
        }
        b.setMessageType(Msg.MessageType.USER_LIST);
        Msg.Message m = b.build();
        return m;
    }
}
