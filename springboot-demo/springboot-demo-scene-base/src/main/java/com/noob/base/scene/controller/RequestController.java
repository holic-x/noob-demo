package com.noob.base.scene.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/request")
public class RequestController {

    // 场景：测试最大请求连接
    @GetMapping("/testMaxConnection")
    public String testMaxConnection() throws InterruptedException {
        log.info("hello world");
        // 模拟沉睡2s
        Thread.sleep(2000);
        return "success";
    }

}
