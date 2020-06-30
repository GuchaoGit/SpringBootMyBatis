package com.guc.springboot.mybatis.dao;

import java.util.List;
import java.util.Map;

/**
 * 用于提取常用的公共方法，减少重复代码。
 * @param <T>
 */
public interface BaseMapper<T> {
    /**
     * 保存
     */
    Integer save(T t);

    /**
     * 删除
     */
    Integer delete(Integer id);

    /**
     * 通过 id 查询
     */
    T findById(Integer id);

    /**
     * 更新
     */
    void update(T t);

    /**
     * 返回所有信息
     */
    List<T> list();

    List<T> listByWhere(Map map);
}
