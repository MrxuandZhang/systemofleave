package com.hongzan.test.daoTest;

import com.hongzan.base.Page;
import com.hongzan.dao.UserDao;
import com.hongzan.entity.Leave;
import com.hongzan.entity.User;
import com.hongzan.service.UserService;
import com.hongzan.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserDaoTest extends BaseTest {
    @Resource
    private UserService userService;
    @Resource
   private UserDao userDao;
    @Test
    public void addTest(){

    }

    @Test
    public void Test(){
      User users=userDao.getOneUser(1);
       System.out.println(users.getUname());
    }

    @Test
    public void loginTest(){
        User user=new User();
        user.setUno("153914z");
        user.setUpwd("123456");
        System.out.println(userService.login(user)==null);
    }
    @Test
    public void queryTest(){
        User user1=new User();
        user1.setLeaNo(new Leave());
      Page<User> list= userService.query(user1,"0","0");
        for (User user:list.getList()) {
            System.out.println(user.getUname()+"\t"+user.getLeaNo().getReason());
        }
     }
}
