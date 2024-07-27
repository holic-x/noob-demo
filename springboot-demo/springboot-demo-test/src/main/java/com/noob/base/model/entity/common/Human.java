package com.noob.base.model.entity.common;

public class Human {
    private int id;
    private String name;
    private int age;

    // 加入自定义构造器会报错（todo）
//    Human(){}
//    Human(int id,String name){
//        this.id = id;
//        this.name = name;
//    }
//    Human(int id, String name, int age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
