package com.noob.base.inheritance;

/**
 * 动物类
 */
public class Animal {

    // 动物公共属性定义
    public int id;
    public String name;
    public int age;
    public int weight;

    // 构造函数
    public Animal(int id, String name, int age, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    // 公共方法定义（此处省略setter、getter）
    public void say(){
        System.out.println("hello world");
    }
    public void eat(){
        System.out.printf("eating...");
    }
}
