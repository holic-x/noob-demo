package com.noob.framework.controller;

import com.noob.framework.model.User;
import com.noob.framework.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public void getAllUser(){
        // 分别调用不同数据源获取信息
        List<User> masterUserList = userService.getMasterUserList();
        log.info("主数据源：{}" , masterUserList.toString());
        List<User> salveUserList = userService.getSalveUserList();
        log.info("副数据源：{}" , salveUserList.toString());
    }

}
