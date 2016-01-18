package com.lhs.weichat.bean;

import java.util.Date;

/**
 * 用户认证码
 * 
 * @author chenbiao
 *
 */
public class UserAuthToken {

	private int id;

	/**
	 * token所属用户
	 */
	/**
	 * 创建人Id
	 */
	private User user;
	/**
	 * 认证token
	 */
	private String token;

	/**
	 * 还是否有效
	 */
	private boolean enable;

	/**
	 * 使用该token的客户端类型
	 */
	private String clientType;
	/**
	 * 创建日期
	 */
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 用户客户端唯一标示
	 */
	private String clientId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public User getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isEnable() {
		return enable;
	}

	public String getClientType() {
		return clientType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
