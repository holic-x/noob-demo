package com.noob.base.scene.disturbtedConcurrent.redisLock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis 环境测试
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    // 测试redis连接
    @GetMapping("/testConn")
    public String testConn(@RequestParam(value = "key") String key, @RequestParam(value = "val") String val) {
        /*
        // 插入数据
        redisTemplate.opsForValue().set("test:key", "hello 123456");
        // 获取数据
        String val = (String) redisTemplate.opsForValue().get("test:key");
        return val;
         */

        // 插入数据
        redisTemplate.opsForValue().set(key, val);
        // 获取数据
        String findVal = (String) redisTemplate.opsForValue().get(key);
        return findVal;
    }

}
