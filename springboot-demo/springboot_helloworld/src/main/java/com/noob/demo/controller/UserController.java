package com.noob.demo.controller;

import com.noob.demo.entity.Result;
import com.noob.demo.entity.User;
import com.noob.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // Rest  /user/1
    @GetMapping("/{id}")
    public Result getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return new Result<>(200, "查询成功", user);
    }

    // 新增 /user/add
    @PostMapping("/add")
    public Result addUser(User user) {
        userService.add(user);
        return new Result<>(200, "添加成功");
    }

    // 修改 /user1
    @PutMapping("/{id}")
    public Result editUser(User user) {
        userService.update(user);
        return new Result<>(200, "修改成功");
    }

    // 修改 /user1
    @DeleteMapping("/{id}")
    public Result editUser(@PathVariable Integer id) {
        userService.delete(id);
        return new Result<>(200, "删除成功");
    }
}
