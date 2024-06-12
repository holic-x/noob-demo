package com.noob.framework.controller;

import com.noob.framework.module.DemoModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoModule demoModule;

    @GetMapping("/execute")
    public String execute(){
        return demoModule.exeModuleMethod();
    }

}
