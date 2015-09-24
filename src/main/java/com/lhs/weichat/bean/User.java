package com.lhs.weichat.bean;

import java.util.Date;

/**
 * User
 *
 * @author longhuashen
 * @since 15/9/24
 */
public class User {

    /**
     * id
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
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别
     */
    private int gender;

    /**
     * 头像
     */
    private Attachement avatar;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Attachement getAvatar() {
        return avatar;
    }

    public void setAvatar(Attachement avatar) {
        this.avatar = avatar;
    }
}
