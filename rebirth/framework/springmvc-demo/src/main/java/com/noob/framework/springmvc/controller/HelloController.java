package com.noob.framework.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 控制器定义
@Controller
@RequestMapping({"hello","myIndex"})
public class HelloController {
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String hello() {
        // 返回的逻辑视图名
        return "index";
    }
}
