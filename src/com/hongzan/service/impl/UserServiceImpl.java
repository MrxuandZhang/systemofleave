package com.hongzan.service.impl;

import com.hongzan.base.Page;
import com.hongzan.dao.UserDao;
import com.hongzan.entity.User;
import com.hongzan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户服务接口的实现
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void del(Serializable sid) {
        userDao.del(sid);
    }

    @Override
    public User get(Serializable sid) {
        return userDao.get(sid);
    }

    @Override
    public Page<User> query(User user, String currentPage, String pageNum) {
        int start=(Integer.parseInt(currentPage)-1)*Integer.parseInt(pageNum);
        return new Page<User>(currentPage,pageNum,userDao.getTotal(user),userDao.queryLimit(user,start,Integer.parseInt(pageNum)));
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> getUsers(String uname) {
        return userDao.getUsers(uname);
    }

    @Override
    public User getOneUser(Integer level) {
        return userDao.getOneUser(level);
    }
}
