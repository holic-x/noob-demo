package com.noob.base.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.noob.base.demo.model.entity.User;
import com.noob.base.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 测试数据源1
    @GetMapping("/findAllByTest1")
    public String findAllByTest1() {
        List<User> userList = userService.findAllByTest1();
        log.info("userList:{}", JSONObject.toJSONString(userList));
        return "success";
    }

    // 测试数据源2
    @GetMapping("/findAllByTest2")
    public String findAllByTest2() {
        List<User> userList = userService.findAllByTest2();
        log.info("userList:{}", JSONObject.toJSONString(userList));
        return "success";
    }
}
