package com.hongzan.dao;

import com.hongzan.base.BaseDao;
import com.hongzan.entity.Audit;
import com.hongzan.entity.User;

import java.util.List;

/**
 * 审核单操作类
 */
public interface AuditDao extends BaseDao<Audit> {
    List<Audit> queryHistory(Audit audit);//查看历史周转
    void updatemes(Audit audit);//修改状态以及建议
    List<Audit> queryNoAudit(Audit level);//获取为未审核单子
    List<Audit> getLeaveHistory(Audit audit);//获取审核请假条的历史流转
}
