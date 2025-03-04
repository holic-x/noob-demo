package com.noob.base.user.controller;

import com.noob.base.multiTenant.TenantContext;
import com.noob.base.user.model.User;
import com.noob.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUsers(@RequestHeader("X-Tenant-Id") Long tenantId) {
        // 设置当前租户 ID
        TenantContext.setCurrentTenant(tenantId);
        try {
            return userService.listUsers();
        } finally {
            // 清除租户上下文
            TenantContext.clear();
        }
    }
}