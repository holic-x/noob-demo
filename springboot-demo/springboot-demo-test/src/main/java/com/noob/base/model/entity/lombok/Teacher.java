package com.noob.base.model.entity.lombok;

import lombok.Data;

@Data
public class Teacher {

    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}