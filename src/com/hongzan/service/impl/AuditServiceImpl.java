package com.hongzan.service.impl;

import com.hongzan.base.Page;
import com.hongzan.dao.AuditDao;
import com.hongzan.entity.Audit;
import com.hongzan.entity.Leave;
import com.hongzan.service.AuditService;
import com.hongzan.service.LeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Service("auditService")
public class AuditServiceImpl implements AuditService {

    @Resource
    private AuditDao auditDao;
    @Resource
    private LeaveService leaveService;

    @Override
    public List<Audit> queryHistory(Audit audit) {
        return auditDao.queryHistory(audit);
    }

    /**
     * 修改建议和状态码
     * 在同意了之后执行
     * @param audit
     */
    @Override
    public void updatemes(Audit audit) {
        //设置修改时间
        audit.setAname(new Date().toLocaleString());
        /* 当对应的等级为三时则修改对应请假单通过 */
        if (audit.getLevel().getLevel()==3){
            Leave leave=new Leave();
            leave.setStateCode(0);
            leave.setLeaNo(audit.getLeaNo().getLeaNo());
            leaveService.updateCode(leave);
        }
        auditDao.updatemes(audit);
    }

    @Override
    public void add(Audit audit) {
        auditDao.add(audit);
    }

    /**
     * 修改全部的状态为驳回
     * 在驳回时使用
     * @param audit
     */
    @Override
    public void update(Audit audit) {
        auditDao.update(audit);
        //修改对应的请假表
        Leave leave=new Leave();
        leave.setStateCode(1);
        leave.setLeaNo(audit.getLeaNo().getLeaNo());
        leaveService.updateCode(leave);
    }

    @Override
    public void del(Serializable sid) {
        auditDao.del(sid);
    }

    @Override
    public Audit get(Serializable sid) {
        return auditDao.get(sid);
    }

    @Override
    public List<Audit> getLeaveHistory(Audit audit) {
        return auditDao.getLeaveHistory(audit);
    }

    @Override
    public List<Audit> queryNoAudit(Audit level) {
        List<Audit> list=auditDao.queryNoAudit(level);
        return list;
    }

    @Override
    public Page<Audit> query(Audit audit, String currentPage, String pageNum) {
        int start=(Integer.parseInt(currentPage)-1)*Integer.parseInt(pageNum);
        return new Page<Audit>(currentPage,pageNum,auditDao.getTotal(audit),auditDao.queryLimit(audit,start,Integer.parseInt(pageNum)));

    }
}
