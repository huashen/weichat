package com.lhs.weichat.bean;


public class ChatGroupMember {
	private User user;

	private ChatGroup chatGroup;
	/**
	 * 备注名称
	 */
	private String remarkName;

	/**
	 * 是否屏蔽消息
	 */
	private boolean shield;

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	public User getUser() {
		return user;
	}

	public ChatGroup getChatGroup() {
		return chatGroup;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setChatGroup(ChatGroup chatGroup) {
		this.chatGroup = chatGroup;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

}
