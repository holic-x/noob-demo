package com.noob.framework.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
