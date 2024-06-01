package com.noob.base.inheritance;

/**
 * Dog类定义
 */
public class Dog extends Animal{
    public Dog(int id, String name, int age, int weight) {
        // 调用父类构造方法
        super(id, name, age, weight);
    }

    public static void main(String[] args) {
        // Dog继承Animal，拥有了Animal的非私有的属性和方法
        Dog dog = new Dog(1,"小白",2,20);
        dog.say();
    }
}
