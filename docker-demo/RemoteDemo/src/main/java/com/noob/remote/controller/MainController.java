package com.noob.remote.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MainController
 * @Description TODO
 * @Author holic-x
 * @Date 2024/5/4 14:41
 */
@RestController
public class MainController {


    @RequestMapping("/health")
    public String health(){
        return "hello world";
    }
}
