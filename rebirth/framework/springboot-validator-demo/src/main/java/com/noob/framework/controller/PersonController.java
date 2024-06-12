package com.noob.framework.controller;

import com.noob.framework.group.Create;
import com.noob.framework.group.Update;
import com.noob.framework.model.Person;
import com.noob.framework.model.RspDTO;
import com.noob.framework.model.User;
import com.noob.framework.model.UserAddDTO;
import com.noob.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class PersonController {
    // 添加
    @GetMapping("/add")
    public String add(@RequestBody @Validated(Create.class) Person person){
        System.out.println("模拟数据操作完成..." + person.toString());
        return "success";
    }

    // 修改
    @GetMapping("/update")
    public String update(@RequestBody @Validated(Update.class) Person person){
        System.out.println("模拟数据操作完成..." + person.toString());
        return "success";
    }

    // 默认分组
    @GetMapping("/defaultGroup")
    public String defaultGroup(@RequestBody @Validated Person person){
        System.out.println("模拟数据操作完成..." + person.toString());
        return "success";
    }
}
