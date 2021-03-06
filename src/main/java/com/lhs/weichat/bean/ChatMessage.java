package com.lhs.weichat.bean;

import java.util.Date;

public class ChatMessage {
	/**
	 * 发送消息
	 */
	public final static int TYPE_SEND = 0;
	/**
	 * 接收消息
	 */
	public final static int TYPE_RECEIVE = 1;

	public final static int MSG_TYPE_UU = 0;// 人人之间的消息
	public final static int MSG_TYPE_UCG = 1;// 群消息
	public final static int MSG_TYPE_UDG = 2;// 讨论组消息

	public final static int CONTENT_TYPE_NORMAL = 0;// 普通消息
	public final static int CONTENT_TYPE_ATTACHMENT = 1;// 为带附件消息

	public final static int STATUS_UNKNOWN = 0;// 未知状态，在服务端不会出现，在客户端会出现
	public final static int STATUS_SEND = 1;// 默认状态已发送
	public final static int STATUS_RECEIVED = 2;// 已收到
	public final static int STATUS_READ = 3;// 已阅读

	private int id;

	/**
	 * 非持久化字段
	 */
	private String token;

	private int fromId;

	private String uuid;


	private int status;


	private int toId;


	private String content;

	/**
	 * 消息内容类型，0为普通消息，1为带附件消息
	 */

	private int contentType;

	/**
	 * 带附件的消息
	 */

	private Attachment attachment;

	private Date date;

	/**
	 * 消息类型，0--发送消息，1--接收消息
	 */

	private int type;
	/**
	 * 消息类型，0--人人消息 1--群消息 2--讨论组消息
	 */

	private int msgType;

	/**
	 * 当msgtype为群消息时不为空
	 */

	private int chatGroupId;

	/**
	 * 当为讨论组消息时不为空
	 */

	private int discussionGroupId;
	// 是否需要转发，如果传入的是clinet则需要转发，如果是服务端则说明本就是转发消息不需要转发
	private boolean transfer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public String getToken() {
		return token;
	}

	public String getContent() {
		return content;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFromId() {
		return fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMsgType() {
		return msgType;
	}

	public int getChatGroupId() {
		return chatGroupId;
	}

	public int getDiscussionGroupId() {
		return discussionGroupId;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public void setChatGroupId(int chatGroupId) {
		this.chatGroupId = chatGroupId;
	}

	public void setDiscussionGroupId(int discussionGroupId) {
		this.discussionGroupId = discussionGroupId;
	}

	public int getContentType() {
		return contentType;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
