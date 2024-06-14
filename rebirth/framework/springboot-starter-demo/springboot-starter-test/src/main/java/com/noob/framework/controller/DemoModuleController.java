package com.noob.framework.controller;

import com.noob.framework.module.DemoModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/demoModule")
public class DemoModuleController {

    @Resource(name = "demoModuleByParam")
    private DemoModule demoModule;

    @GetMapping("/execute")
    public String execute(){
        return demoModule.exeModuleMethod();
    }

}
