package com.noob.framework.springmvc.controller;

import com.noob.framework.springmvc.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class DemoRestController {
    @RequestMapping(value = "/getJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getJson(@RequestBody User user) {
        return new User(user.getName(), user.getAge());
    }
}

