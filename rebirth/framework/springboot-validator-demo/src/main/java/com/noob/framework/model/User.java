package com.noob.framework.model;

import lombok.Data;

import java.util.Date;

/**
 * User类定义
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date createTime;

    public User(String username) {
        this.username = username;
    }
}
