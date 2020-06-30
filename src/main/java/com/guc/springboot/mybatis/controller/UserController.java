package com.guc.springboot.mybatis.controller;

import com.guc.springboot.mybatis.entity.User;
import com.guc.springboot.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author guc
 * @Date 2020/4/8 14:54
 * @Description Controller 层
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    // 使用 post 请求新建
    @PostMapping()
    public String save(User user){
        userService.save(user);
        return "save success";
    }

    // 使用 put 请求更新，会拦截类似 /user/1 这种形式的路径
    @PutMapping("{id}")
    public String update(User user, @PathVariable int id){
        // 当数据不存在时，不允许更新
        if (userService.findById(id) == null){
            return "Not Exist";
        }
        // 防止传入的 id 不一致，如 user 中 id 属性是 2，而 url 路径中 id 为 1
        if (user.getId() != id){
            return "Unmatched parameters";
        }
        userService.update(user);
        return "update success";
    }

    // 使用 delete 请求删除
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable int id){
        int rest = userService.delete(id);
        if (rest>0)
        return "delete success";
        else return "delete failure";
    }

    // 使用 get 方法查询
    @GetMapping()
    public List<User> list(){
        return userService.list();
    }

    @RequestMapping("query")
    public List<User> listByWhere(@RequestParam Map map){
        return userService.listByWhere(map);
    }

    // 使用 get 方法查询单个数据
    @GetMapping("{id}")
    public User getById(@PathVariable int id){
        return userService.findById(id);
    }
}
