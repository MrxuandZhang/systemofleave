package com.hongzan.entity;

import com.hongzan.base.BaseEntity;

/**
 * 审核单实体类
 */
public class Audit extends BaseEntity {
    private Leave leaNo;//单号
    private String aname;//审核人
    private String suggest;//建议
    private Integer statecode;//状态码 状态码:0:通过1:驳回2:正在审核
    private User level;//审核等级
    private String atime;//审核时间

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public Leave getLeaNo() {
        return leaNo;
    }

    public void setLeaNo(Leave leaNo) {
        this.leaNo = leaNo;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
    }

    public User getLevel() {
        return level;
    }

    public void setLevel(User level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "leaNo=" + leaNo +
                ", aname='" + aname + '\'' +
                ", suggest='" + suggest + '\'' +
                ", statecode=" + statecode +
                ", level=" + level +
                ", atime='" + atime + '\'' +
                '}';
    }
}
