package com.noob.framework.springmvc.dao;

import com.noob.framework.springmvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImpl {

    public List<User> findUser(){
        return Collections.singletonList(new User("noob",18));
    }

}
