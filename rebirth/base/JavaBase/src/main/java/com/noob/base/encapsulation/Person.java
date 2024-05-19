package com.noob.base.encapsulation;


public class Person {

    /**
     * 对属性的封装：姓名、年龄、性别
     */
    private String name;
    private int age;
    private String gender;
    
    /**
     * 对外提供getter、setter方法（供外部程序访问）
     * 可在方法中加入逻辑校验，控制外部程序对成员变量的访问
     */
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
