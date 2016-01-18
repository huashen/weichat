package com.lhs.weichat.bean;

import java.io.Serializable;
import java.util.Date;

public class AppVersion implements Serializable {

    private int id;

    private int versionCode;

    private String downloadUrl;

    private Date createTime;

    private Date publishTime;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
