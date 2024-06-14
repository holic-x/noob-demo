package com.noob.framework.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 控制器定义
@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        System.out.println("------controller index方法执行------");
        return "success";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        System.out.println("------controller login方法执行------");
        return "login success";
    }
}
