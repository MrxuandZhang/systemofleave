package com.hongzan.service;

import com.hongzan.base.BaseService;
import com.hongzan.entity.User;

import java.util.List;

/**
 * 用户服务类接口
 */
public interface UserService extends BaseService<User> {
    User login(User user);
    User getOneUser(Integer level);
    List<User> getUsers(String uname);

}
