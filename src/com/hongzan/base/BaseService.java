package com.hongzan.base;

import java.io.Serializable;

/**
 * 基础业务类
 *
 * @param <T> 泛型
 */
public interface BaseService<T> {
    /**
     * 添加实体对象
     * @param t 实体对象
     */
    void add(T t);
    /**
     * 修改实体对象
     * @param t 实体对象
     */
    void update(T t);
    /**
     * 删除实体对象
     * @param sid 对应id属性
     */
    void del(Serializable sid);
    /**
     * 获取实体对象
     * @param sid 对象id
     * @return 对象
     */
    T get(Serializable sid);

    /**
     * 分页查询
     * @param t 查询对象
     * @param currentPage 当前页
     * @param pageNum 页面数
     * @return page对象
     */
    Page<T> query(T t, String currentPage, String pageNum);
}
