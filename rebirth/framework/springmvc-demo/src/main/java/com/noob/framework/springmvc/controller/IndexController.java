package com.noob.framework.springmvc.controller;

import com.noob.framework.springmvc.model.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 控制器定义
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "toIndex")
    public String index() {
        // 跳转到index页面
        return "index";
    }

    @RequestMapping(value = "toLogin")
    public String toLogin() {
        // 跳转到登录页面
        return "login";
    }

    @RequestMapping(value = "login")
    public String login(LoginUser loginUser, HttpSession session, HttpServletRequest request) {
        // 执行登录操作，模拟默认账号验证
        String defaultUsername = "noob";
        String defaultPassword = "123456";
        if(defaultUsername.equals(loginUser.getUsername()) && defaultPassword.equals(loginUser.getPassword())) {
            // 保存session记录（此处只是简单保存信息，正常保存登录信息为用户实体）
            session.setAttribute("current_user", loginUser);
            // 登录成功后跳转主页
            return "redirect:/index/toIndex";
        }else{
            request.setAttribute("message", "用户名或密码错误");
            // 登录失败后跳转登录页面
            return "redirect:/index/login";
        }
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        // 注销操作
        session.invalidate();
        // 注销成功跳转登录页面
        return "redirect:/index/login";
    }
}
