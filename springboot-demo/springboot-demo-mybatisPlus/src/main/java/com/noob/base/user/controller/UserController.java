package com.noob.base.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.noob.base.user.model.entity.User;
import com.noob.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateById(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String keyword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", keyword).or().like("email", keyword);
        return userService.list(queryWrapper);
    }
}