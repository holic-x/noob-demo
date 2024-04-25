package com.noob.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hi")
    @ResponseBody
    public String hello(){
        System.out.println("hello world");
        return "hello";
    }

    @RequestMapping("/getName")
    @ResponseBody
    public String getName(@RequestParam String name){
        return "Hello-" + name;
    }
}
