package com.noob.framework.di.autowire;

import lombok.Data;

@Data
public class Student {

    private String stuName;

    private String stuId;

    public Student(String stuName, String stuId) {
        this.stuName = stuName;
        this.stuId = stuId;
    }
}
