package com.lhs.weichat.bean;

/**
 * 用户所在线的服务器
 * 
 * @author chenbiao
 *
 */
public class UserOnlineServer {

	public final static int ONLINE_STATUS_WANNA_TALK = 1;// 求聊

	public final static int ONLINE_STATUS_ONLINE = 2;// 在线

	public final static int ONLINE_STATUS_BUSY = 3;// 忙碌

	public final static int ONLINE_STATUS_NO_DISTURB = 4;// 勿扰

	public final static int ONLINE_STATUS_LEAVE = 5;// 离开

	public final static int ONLINE_STATUS_INVISIBLE = 6;// 隐身

	public final static int ONLINE_STATUS_OFFLINE = 7;// 离线

	/**
	 * id
	 */
	private int id;

	private UserAuthToken userAuthToken;

	private ChatServer chatServer;

	/**
	 * 在线状态
	 */
	private int onlineStatus;

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
}
