package com.noob.framework.springmvc.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private int age;
    public User() {}
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
