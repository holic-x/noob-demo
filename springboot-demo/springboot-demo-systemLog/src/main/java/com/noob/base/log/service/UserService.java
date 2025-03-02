package com.noob.base.log.service;

import com.noob.base.log.anno.Loggable;
import org.springframework.stereotype.Service;

@Service("log-UserService")
public class UserService {

    @Loggable
    public String getUserById(Long id) {
        return "User " + id;
    }
}