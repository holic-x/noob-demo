package com.noob.base.scene.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/math")
public class MathController {

    // 模拟生成随机文本信息
    @GetMapping("/getRandomContext")
    public String getRandomContext() {
        while (true) {
            log.info("模拟生成数据：{}", UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }

    // 模拟触发异常
    @GetMapping("/triggerException")
    public String triggerException() {
        throw new RuntimeException("异常触发，模拟程序执行异常");
    }

    // 模拟执行耗时
    @GetMapping("/doSth")
    public String doSth() throws InterruptedException {
        log.info("模拟生成数据：{}", UUID.randomUUID().toString().replaceAll("-", ""));
        Thread.sleep(2000); // 沉睡2s 模拟耗时
        return "success";
    }




}
