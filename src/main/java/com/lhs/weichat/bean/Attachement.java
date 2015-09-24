package com.lhs.weichat.bean;

import java.util.Date;

/**
 * Attachement
 *
 * @author longhuashen
 * @since 15/9/24
 */
public class Attachement {

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
     * 文件路径
     */
    private String path;

    /**
     * 文件类型
     */
    private int type;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date createDate;

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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
