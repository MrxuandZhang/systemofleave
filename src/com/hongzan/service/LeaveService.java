package com.hongzan.service;

import com.hongzan.base.BaseService;
import com.hongzan.entity.Leave;

import java.util.List;

/**
 * 请假单服务接口
 */
public interface LeaveService extends BaseService<Leave> {
        List<Leave> getPerson(Leave leave);
        void updateCode(Leave leave);
}
