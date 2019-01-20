package com.hongzan.entity;

import com.hongzan.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 请假单实体类
 */
public class Leave extends BaseEntity {
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private String begin;//开始请假时间
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private String end;//结束请假时间
    private String reason;//请假类型
    private String reasonContent;//请假原因
    private String leaNo;//请假单号
    private User person;//请假人
    private Integer stateCode;
    private List<Upload> list;

    public List<Upload> getList() {
        return list;
    }

    public void setList(List<Upload> list) {
        this.list = list;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getReasonContent() {
        return reasonContent;
    }

    public void setReasonContent(String reasonContent) {
        this.reasonContent = reasonContent;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaNo() {
        return leaNo;
    }


    public void setLeaNo(String leaNo) {
        this.leaNo = leaNo;
    }
}
