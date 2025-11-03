package com.noob.base.runtimeFailures.oom.service;

import com.noob.base.demo.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("oom-UserService")
public class UserService {

    public void createUser() {
        List<User> userList = new ArrayList<>();
        int cnt = 1;
        while (true) {
            User newUser = User.builder().id(Long.valueOf(cnt)).name("noob-" + cnt).email(cnt + "@126.com").build();
            log.info("创建用户={}", newUser);
            userList.add(newUser);
            cnt++;
        }
    }

}
