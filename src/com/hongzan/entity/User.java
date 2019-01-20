package com.hongzan.entity;

import com.hongzan.base.BaseEntity;

/**
 * 用户实体类
 */
public class User extends BaseEntity {
    private String uno;//员工编号 使用于登录
    private String uname;//员工姓名
    private String upwd;//密码
    private Leave leaNo;//请假单号
    private Integer level;//等级 :员工1:一级管理员 2:二级管理员3:三级管理员etc.

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public Leave getLeaNo() {
        return leaNo;
    }

    public void setLeaNo(Leave leaNo) {
        this.leaNo = leaNo;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "uno='" + uno + '\'' +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", leaNo=" + leaNo +
                ", level=" + level +
                '}';
    }
}
