package com.noob.oom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OomController {

    @RequestMapping("/testThreadLocalOom")
    public String testThreadLocalOom(HttpServletRequest request) {
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
        localVariable.set(new Byte[4096*10240]);// 为线程添加变量
        System.out.println("ThreadLocalOom执行");
        return "success";
    }

}
