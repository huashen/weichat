package com.lhs.weichat.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 文件附件
 *
 */
public class Attachment implements Serializable {

	public static final int TYPE_UNKNOWN = 0;// 未知文件类型

	public static final int TYPE_JPG = 1;

	public static final int TYPE_PNG = 2;

	public static final int TYPE_GIF = 3;

	public static final int TYPE_DOC = 4;

	public static final int TYPE_DOCX = 5;

	public static final int TYPE_PPT = 6;

	public static final int TYPE_PPTX = 7;

	public static final int TYPE_XLS = 8;

	public static final int TYPE_XLSX = 9;

	public static final int TYPE_PDF = 10;

	public static final int TYPE_TXT = 11;

	public static final int TYPE_RAR = 12;

	public static final int TYPE_ZIP = 13;

	public static final int TYPE_VOICE = 101;// 语音

	public static final int TYPE_VIDEO = 102;// 视频

	/**
	 * id
	 */
	private int id;

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 对应在FastDFS上的group
	 */
	private String groupName;

	/**
	 * 文件位置，所有的文件都存在网络上
	 */
	private String path;

	/**
	 * 文件类型
	 */
	private int type;

	/**
	 * 用户id，非持久化字段
	 */
	private int userId;

	/**
	 * 用户token
	 */
	private String token;

	/**
	 * 文件大小
	 */
	private long size;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
