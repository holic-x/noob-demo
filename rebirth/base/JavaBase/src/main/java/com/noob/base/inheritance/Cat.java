package com.noob.base.inheritance;

/**
 * Cat定义
 */
public class Cat extends Animal{
    public Cat(int id, String name, int age, int weight) {
        // 调用父类构造方法
        super(id, name, age, weight);
    }

    // 猫会额外喵喵叫
    public void meow(){
        System.out.println("miao...");
    }
}
