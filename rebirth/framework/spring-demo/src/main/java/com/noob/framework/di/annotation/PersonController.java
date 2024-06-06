package com.noob.framework.di.annotation;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

// Bean注册（将Controller控制层的PersonController标识为spring的bean）
@Controller("personController")
public class PersonController {
    @Resource(name = "personService")
    private PersonService personService;

    public void add(){
        System.out.println("PersonController add方法执行");
        personService.addUser("noob");
    }
}
