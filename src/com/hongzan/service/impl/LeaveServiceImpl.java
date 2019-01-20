package com.hongzan.service.impl;

import com.hongzan.base.Page;
import com.hongzan.base.UserException;
import com.hongzan.dao.AuditDao;
import com.hongzan.dao.LeaveDao;
import com.hongzan.dao.UserDao;
import com.hongzan.entity.Audit;
import com.hongzan.entity.Leave;
import com.hongzan.entity.User;
import com.hongzan.service.LeaveService;
import com.hongzan.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 请假单服务接口实现类
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
    @Resource
    private LeaveDao leaveDao;//请假单操作接口
    @Resource
    private AuditDao auditDao;//审核单操作接口
    @Resource
    private UserDao userDao;//审核单操作接口

    @Override
    public void add(Leave leave) {
        leaveDao.add(leave);
        //添加多张申请单
        Audit ad = new Audit();
        ad.setLeaNo(leave);
        ad.setStatecode(2);
        ad.setAtime("正在审核");
        //获取添加用户 当前级别的申请人自己就无需添加一条审核消息
        List<User> list=userDao.getUsers(leave.getPerson().getUname());
        for(int i =0;i<list.size();i++){
            //获取对应级别的用户添加
            User users=userDao.getOneUser(list.get(i).getLevel());
            if(!CommonUtil.isNull(users)) {
                ad.setId(CommonUtil.getId());
                ad.setAname(users.getUname());
                ad.setSuggest("无");
                ad.setLevel(users);
                auditDao.add(ad);
            }else{
                //当没有对应的上级信息时
                throw new UserException("上级未存在无法请假！");
            }
        }

    }

    @Override
    public void update(Leave leave) {
        leaveDao.update(leave);
    }

    @Override
    public void del(Serializable sid) {
        leaveDao.del(sid);
    }

    @Override
    public Leave get(Serializable sid) {
        return leaveDao.get(sid);
    }

    @Override
    public Page<Leave> query(Leave leave, String currentPage, String pageNum) {
        int start=(Integer.parseInt(currentPage)-1)*Integer.parseInt(pageNum);
        return new Page<Leave>(currentPage,pageNum,leaveDao.getTotal(leave),leaveDao.queryLimit(leave,start,Integer.parseInt(pageNum)));

    }

    @Override
    public List<Leave> getPerson(Leave leave) {
        return leaveDao.getPerson(leave);
    }

    @Override
    public void updateCode(Leave leave) {
        leaveDao.updateCode(leave);
    }
}
