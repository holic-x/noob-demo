package com.noob.base.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.noob.base.demo.model.entity.User;
import com.noob.base.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(User user) {
        boolean res = userService.save(user);
        if(res){
            return "success";
        }else {
            return "fail";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer id) {
        boolean res = userService.removeById(id);
        if(res){
            return "success";
        }else {
            return "fail";
        }
    }

    @PostMapping("/update")
    public String update(User user) {
        boolean res = userService.updateById(user);
        if(res){
            return "success";
        }else {
            return "fail";
        }
    }

    @PostMapping("/get")
    public User get(@RequestParam Integer id) {
        User findUser = userService.getById(id);
        return findUser;
    }

    @PostMapping("/getByCond")
    public List<User> getByCond(@RequestParam String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<User> userList = userService.list(queryWrapper);
        return userList;
    }
}
