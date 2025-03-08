package com.noob.base.user.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // 普通接口
    @GetMapping("/getName")
    public String getName() {
        return "hello";
    }

    // 带单个参数
    @GetMapping("/showName/{name}")
    public String showName(@PathVariable String name) {
        return name;
    }

    // 带多个参数
    @GetMapping("/showInfo")
    public String showInfo(@RequestParam String name,@RequestParam int age) {
        return "name : " + name + " age : " + age;
    }

    // 请求参数为实体类型
    @PostMapping("/showJson")
    public String showJson(@RequestBody JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    // 带header校验
    @GetMapping("/getToken")
    public String showNameWithHeader(@RequestHeader(name = "userToken") String userToken) {
        return "userToken: " + userToken;
    }

}
