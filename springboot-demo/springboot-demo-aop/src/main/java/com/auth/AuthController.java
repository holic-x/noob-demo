package com.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // http://localhost:9082/demo/auth/aopTest?name=hhh
    @GetMapping("/aopTest")
    @Auth
    public String aopTest(@RequestParam String name) {
        System.out.println("正在执行接口name" + name);
        return "执行成功" + name;
    }
}