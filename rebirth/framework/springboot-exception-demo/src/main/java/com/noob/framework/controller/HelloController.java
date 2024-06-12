package com.noob.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloController {

    // 1.方法内部出现异常
    @GetMapping("/1")
    public String one(){
        System.out.println("进入方法1测试....");
        // 模拟异常：抛出除数不能为0的异常
        int i = 10/0;
        return "success";
    }

    // 2.controller向上继续抛出异常
    @GetMapping("/2")
    public String two() throws Exception {
        System.out.println("进入方法2测试....");
        throw  new Exception("controller 手动抛出异常");
    }

    // 3.模拟没进入方法前就出现异常的场景（例如请求异常等情况）
    @GetMapping("/3")
    public String three(@RequestParam String name) {
        System.out.println("进入方法3测试....");
        return "success:" + name;
    }

}
