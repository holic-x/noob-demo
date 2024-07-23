package com.noob.base.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/getName")
    public String getName() {
        return "hello";
    }

    @GetMapping("/showInfo")
    public String showInfo(@RequestParam String name,@RequestParam int age) {
        return "name : " + name + " age : " + age;
    }

    @PostMapping("/showJson")
    public String showJson(@RequestBody JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    @GetMapping("/showNameWithHeader/{name}")
    public String showNameWithHeader(@PathVariable String name) {
        return name;
    }

}
