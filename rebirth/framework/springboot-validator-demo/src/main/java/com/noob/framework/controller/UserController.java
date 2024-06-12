package com.noob.framework.controller;

import com.noob.framework.model.RspDTO;
import com.noob.framework.model.User;
import com.noob.framework.model.UserAddDTO;
import com.noob.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 1.传统串行校验方式
    @GetMapping("/add")
    public String add(@RequestBody User user){
        userService.save(user);
        return "success";
    }

    // 2.validator校验方式
    @GetMapping("/addByValidator")
    public String addByValidator(@RequestBody @Validated UserAddDTO userAddDTO){
        userService.saveByValidator(userAddDTO);
        return "success";
    }

    @GetMapping("/test")
    public ResponseEntity<RspDTO> test(){
//        int i = 10/0;
        return ResponseEntity.ok(new RspDTO(1,"success"));
    }

    @GetMapping("/testExc")
    public void testExc() throws HttpMediaTypeNotAcceptableException {
        throw new HttpMediaTypeNotAcceptableException("参数异常....");
    }

    @GetMapping("/testUser")
    public User testUser(){
        return new User("noob");
    }

    @GetMapping("/testRspDTO")
    public RspDTO testRspDTO(){
        return new RspDTO(1,"响应成功");
    }

    @GetMapping("/testStr")
    public ResponseEntity<String> test1(){
        return ResponseEntity.ok("success");
    }

}
