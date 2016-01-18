package com.lhs.weichat.bean;

public class ChatServer {

	private int id;

	private String ip;// ip地址

	private int port;// 监听端口

	private boolean online;// 在线

	private String name;//名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public boolean isOnline() {
		return online;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setOnLine(boolean online) {
		this.online = online;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
