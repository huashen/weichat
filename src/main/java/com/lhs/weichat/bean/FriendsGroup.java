package com.lhs.weichat.bean;

/**
 * 好友分组
 * 
 * @author chenbiao
 *
 */
public class FriendsGroup {

	private int id;

	/**
	 * 谁的好友组
	 */
	private User user;

	/**
	 * 分组名称
	 */
	private String name;

	public User getUser() {
		return user;
	}

	/**
	 * 排列顺序
	 */
	private int position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
