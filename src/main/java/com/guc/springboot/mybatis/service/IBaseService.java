package com.guc.springboot.mybatis.service;

import java.util.List;

/**
  *将 service 常用的公共方法抽象出来，如简单的增删查改等功能，这样可以减少很多的重复代码。
  */
public interface IBaseService<T> {
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
}
