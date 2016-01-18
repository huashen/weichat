package com.lhs.weichat.bean;

import java.util.Date;

/**
 * 待办事项
 * 
 * @author chenbiao
 *
 */
public class Todo {

	public final static int TODO_TYPE_ADD_FRIENDS = 1;// 添加好友

	public final static int TODO_TYPE_JOIN_GROUP = 2;// 别人申请加群请求

	public final static int TODO_TYPE_ADD_CHAT_GROUP = 3;// 添加群

	public final static int TODO_TYPE_ADD_DISCUSSATION = 4;// 添加讨论组

	private int id;

	/**
	 * 用户id
	 */
	private User user;

	/**
	 * 待办类型
	 */
	private int type;

	/**
	 * 发起人ID
	 */
	private User from;

	/**
	 * 如果是入群请求则该字段不能为空
	 */
	private ChatGroup group;

	/**
	 * 发起时间
	 */
	private Date createDate;

	/**
	 * 是否已完成
	 */
	private boolean complete;

	/**
	 * 是否同意
	 */
	private boolean agree;

	/**
	 * 请求消息
	 */
	private String requestMsg;

	/**
	 * 处理意见
	 */
	private String handleMsg;

	/**
	 * 处理时间
	 */
	private Date handleDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getUser() {
		return user;
	}

	public int getType() {
		return type;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public boolean isComplete() {
		return complete;
	}

	public boolean isAgree() {
		return agree;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public String getHandleMsg() {
		return handleMsg;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public ChatGroup getGroup() {
		return group;
	}

	public void setGroup(ChatGroup group) {
		this.group = group;
	}
}
