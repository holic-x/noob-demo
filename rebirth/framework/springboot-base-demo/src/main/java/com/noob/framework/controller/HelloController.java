package com.noob.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ServiceLoader;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/index")
    public String index(){
        return "success";
    }
}
