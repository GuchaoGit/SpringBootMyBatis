package com.guc.springboot.mybatis.service.impl;

import com.guc.springboot.mybatis.dao.BaseMapper;
import com.guc.springboot.mybatis.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author guc
 * @Date 2020/4/8 14:30
 * @Description 用于实现公共的增删查改等功能。
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    protected BaseMapper<T> baseMapper;

    @Override
    public Integer delete(Integer id){
        return baseMapper.delete(id);
    }

    @Override
    public Integer save(T t){
        return baseMapper.save(t);
    }

    @Override
    public void update(T t){
        baseMapper.update(t);
    }

    @Override
    public T findById(Integer id){
        return baseMapper.findById(id);
    }


    @Override
    public List<T> list(){
        return baseMapper.list();
    }

    @Override
    public List<T> listByWhere(Map map) {
        return baseMapper.listByWhere(map);
    }
}
