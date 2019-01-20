package com.hongzan.dao;

import com.hongzan.base.BaseDao;
import com.hongzan.entity.Leave;

import java.util.List;

/**
 * 请假单操作类
 */
public interface LeaveDao extends BaseDao<Leave> {
    List<Leave> getPerson(Leave leave);
    void updateCode(Leave leave);
}
