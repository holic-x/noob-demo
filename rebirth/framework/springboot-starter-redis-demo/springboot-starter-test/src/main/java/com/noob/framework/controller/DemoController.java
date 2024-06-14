package com.noob.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private Jedis jedis;

    @GetMapping("/link")
    public String link(){
        // 测试获取redis
        System.out.println(jedis);
        // 测试jedis连接配置
        jedis.set("hello","hello starter");
        return "success:" + jedis.get("hello");
    }

}
