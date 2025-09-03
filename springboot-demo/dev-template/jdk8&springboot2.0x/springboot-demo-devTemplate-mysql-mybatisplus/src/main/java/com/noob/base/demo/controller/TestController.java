package com.noob.base.demo.controller;

import com.noob.base.demo.entity.model.User;
import com.noob.base.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private UserMapper userMapper;
    
    @GetMapping("/config")
    public String testConfig() {
        try {
            List<User> users = userMapper.selectList(null);
            return "MyBatis-Plus配置成功！";
        } catch (Exception e) {
            return "配置错误: " + e.getMessage();
        }
    }
}