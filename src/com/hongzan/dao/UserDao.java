package com.hongzan.dao;

import com.hongzan.base.BaseDao;
import com.hongzan.entity.User;

import java.util.List;

/**
 * 用户操作类
 */
public interface UserDao extends BaseDao<User> {
    User login(User user);//登录查询
    User getOneUser(Integer level);
    List<User> getUsers(String uname);
}
