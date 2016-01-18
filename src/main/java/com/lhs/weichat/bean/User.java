package com.lhs.weichat.bean;

import java.util.Date;

public class User {

	public static int GENDER_MALE = 0;// 男性
	public static int GENDER_FEMALE = 1;// 女性
	public static int GENDER_UNKNOWN = 2;// 未知

	/**
	 * 用户id
	 */
	private int id;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 头像路径
	 */
	private int avatarId;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 个性签名
	 */
	private String signature;

	/**
	 * 个人性别，0男1女2未知
	 */
	private int gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
}
