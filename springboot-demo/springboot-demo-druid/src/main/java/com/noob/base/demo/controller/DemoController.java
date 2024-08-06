package com.noob.base.demo.controller;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // 普通GET请求
    @GetMapping("/getName")
    public String getName(@RequestParam String name) {
        System.out.println(name);
        return name;
    }

    // 普通POST请求
    @PostMapping("/showInfo")
    public String showInfo(@RequestHeader HttpHeaders headers,@RequestParam String name,@RequestParam int age) {
        System.out.println(headers.get("USER_TOKEN"));
        System.out.println(headers.get("Host"));
        System.out.println("name:"+name+",age:"+age);
        return name;
    }

    // POST请求(JSON数据)
    @PostMapping("/showJson")
    public String showJson(@RequestHeader(name = "USER_TOKEN") String userToken,@RequestHeader(name = "Host") String host,@RequestBody JSONObject jsonObject) {
        String res = jsonObject.toJSONString();
        System.out.println("userToken:"+userToken+",host:"+host+",res:"+res);
        return res;
    }

}
