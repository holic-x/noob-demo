package com.noob.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @GetMapping("/get/{key}")
    public Object get(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }
}