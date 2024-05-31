package com.noob.jvm;

public class Student {

    private static final int AGE = 10;

    private String id;
    private String name;
    private int age;

    Student(String id, String name, int age) {
        id = "id_0001";
        name = "noob";
        age = AGE;
    }

    public String getStu() {
        return id + ":" + name + ":" + age;
    }
}
