package com.hongzan.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作类父类接口
 *
 * @param <T> 泛型对象
 */
public interface BaseDao<T> {
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
    void del(java.io.Serializable sid);

    /**
     * 获取实体对象
     * @param sid 对象id
     * @return 对象
     */
    T get(java.io.Serializable sid);

    /**
     * 获取总记录条数
     * @param t 查询条件
     * @return 条数
     */
    int getTotal(@Param("t") T t);

    /**
     * 分页查询
     * @param t 查询对象
     * @param currentPage 当前页
     * @param pageNum 页面数
     * @return 查询集合
     */
    List<T> queryLimit(@Param("t") T t, @Param("start") Integer currentPage, @Param("pageNum") Integer pageNum);
}
