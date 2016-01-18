package com.lhs.weichat.bean;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class AppVersion implements Serializable {

    private int id;

    private int versionCode;
    @Column(length = 250)
    private String downloadUrl;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
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
