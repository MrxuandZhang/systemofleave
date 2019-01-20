package com.hongzan.test.daoTest;

import com.hongzan.base.Page;
import com.hongzan.dao.LeaveDao;
import com.hongzan.entity.Leave;
import com.hongzan.entity.User;
import com.hongzan.service.LeaveService;
import com.hongzan.service.UserService;
import com.hongzan.test.BaseTest;
import com.hongzan.util.CommonUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

public class LeaveDaoTest extends BaseTest {
    @Resource
    private LeaveService leaveService;
    @Resource
    private UserService userService;

    @Test
    public void query(){
        Leave leave=new Leave();
        leave.setPerson(new User());
        Page<Leave> list =leaveService.query(leave,"0","5");
        for (Leave l:list.getList()  ) {
            System.out.println(l.getReason()+"\t"+l.getName()+"\t"+l.getLeaNo());
        }
    }

    @Test
    public void add(){
        Leave leave=new Leave();
        leave.setBegin(new Date().toLocaleString());
        leave.setEnd(new Date(2018,11,2).toLocaleString());
        leave.setLeaNo(CommonUtil.getTime());
        User user=userService.get("4471490ccde44ce5a78674cccb774d7e");
        leave.setPerson(user);
        leave.setReason("产假");
        leave.setId(CommonUtil.getId());
        leaveService.add(leave);
    }
}
