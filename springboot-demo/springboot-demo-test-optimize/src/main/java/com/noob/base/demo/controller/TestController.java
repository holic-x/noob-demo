package com.noob.base.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.noob.base.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    // 普通接口
    @GetMapping("/test01")
    public String test01() {
        testService.test01();
        return "执行成功";
    }


    // 普通接口
    @GetMapping("/test02")
    public String test02() {
        testService.test02();
        return "执行成功";
    }



}
