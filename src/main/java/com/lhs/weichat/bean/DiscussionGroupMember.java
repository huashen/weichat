package com.lhs.weichat.bean;

public class DiscussionGroupMember {

	private User user;


	private DiscussionGroup discussionGroup;
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

	public DiscussionGroup getDiscussionGroup() {
		return discussionGroup;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDiscussionGroup(DiscussionGroup discussionGroup) {
		this.discussionGroup = discussionGroup;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

}
