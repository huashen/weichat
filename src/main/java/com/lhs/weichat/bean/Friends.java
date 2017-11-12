package com.lhs.weichat.bean;

public class Friends {

	/**
	 * id
	 */
	private int id;

	/**
	 * 正常状态
	 */
	public final static int VISIBLE_ONLINE_NORMAL = 0;
	/**
	 * 在线隐身
	 */
	public final static int VISIBLE_ONLINE_INVISIBLE = 1;
	/**
	 * 隐身可见
	 */
	public final static int VISIBLE_INVISIBLE_VISIBLE = 2;

	private User user;

	private User friend;

	/**
	 * 备注名称
	 */
	private String remarkName;

	/**
	 * 是否被屏蔽消息
	 */
	private boolean shield;
	/**
	 * 该朋友所在的组
	 */
	private FriendsGroup friendsGroup;

	/**
	 * 好友对自己的可见状态,在线对其隐身，隐身可见
	 */
	private int visible;

	/**
	 * 用户在线状态，非持久化字段
	 */
	private int onlineStatus;

	/**
	 * 用户在线方式，非持久化字段
	 */
	private int onlineType;


	private Integer friendId;

	private Integer friendsGroupId;

	private Integer userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FriendsGroup getFriendsGroup() {
		return friendsGroup;
	}

	public void setFriendsGroup(FriendsGroup friendsGroup) {
		this.friendsGroup = friendsGroup;
	}

	public User getUser() {
		return user;
	}

	public User getFriend() {
		return friend;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public int getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public Integer getFriendsGroupId() {
		return friendsGroupId;
	}

	public void setFriendsGroupId(Integer friendsGroupId) {
		this.friendsGroupId = friendsGroupId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
