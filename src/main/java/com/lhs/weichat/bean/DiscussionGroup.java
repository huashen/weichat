package com.lhs.weichat.bean;

import java.util.Date;

/**
 * 讨论组
 * 
 * @author chenbiao
 *
 */
public class DiscussionGroup {
	/**
	 * 讨论组名称
	 */
	private String name;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 创建者
	 */
	private User createBy;

	public String getName() {
		return name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

}
