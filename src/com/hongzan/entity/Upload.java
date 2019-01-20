package com.hongzan.entity;

import com.hongzan.base.BaseEntity;

/**
 * 附件上传类
 */
public class Upload extends BaseEntity {
    private String upath;
    private Integer uid;

    private Leave leaNo;

    public Leave getLeaNo() {
        return leaNo;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setLeaNo(Leave leaNo) {
        this.leaNo = leaNo;
    }

    public String getUpath() {
        return upath;
    }

    public void setUpath(String upath) {
        this.upath = upath;
    }
}
