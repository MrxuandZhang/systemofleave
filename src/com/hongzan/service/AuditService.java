package com.hongzan.service;

import com.hongzan.base.BaseService;
import com.hongzan.entity.Audit;

import java.util.List;

/**
 * 审核单服务接口
 */
public interface AuditService extends BaseService<Audit> {
    List<Audit> queryHistory(Audit audit);
    void updatemes(Audit audit);
    List<Audit> queryNoAudit(Audit level);
    List<Audit> getLeaveHistory(Audit audit);
}
