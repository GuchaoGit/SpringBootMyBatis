package com.guc.springboot.mybatis.service.impl;

import com.guc.springboot.mybatis.entity.User;
import com.guc.springboot.mybatis.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author guc
 * @Date 2020/4/8 14:40
 * @Description
 * UserServiceImpl.java 用于实现和 User 有关的业务逻辑。如果对 BaseServiceImpl.java 提供的公用增删查改功能不满意，可以选择直接覆盖或者新填一个方法。
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
}
