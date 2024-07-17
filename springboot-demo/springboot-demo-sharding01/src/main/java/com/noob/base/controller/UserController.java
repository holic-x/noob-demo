package com.noob.base.controller;

import com.noob.base.mapper.UserMapper;
import com.noob.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camps/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/addUser")
    public String addUser(@RequestParam("name") String name, @RequestParam("createTime") String createTime, @RequestParam("age") int age) {
        User user = new User()
                .withName(name)
                .withCreateTime(createTime)
                .withAge(age);
        userMapper.insert(user);
        return "success";
    }

}
