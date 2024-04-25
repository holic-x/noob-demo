package com.example.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * aopController
 */
@RestController
@RequestMapping("/aopController")
public class AopController {

    @RequestMapping(value = "/sayHello")
    public String sayHello(){
        return "hello " + "hhhhh";
    }
}