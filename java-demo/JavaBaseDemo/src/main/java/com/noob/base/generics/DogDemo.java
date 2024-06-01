package com.noob.base.generics;

import java.util.ArrayList;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 15:42
 */
public class DogDemo {
    public static void main(String[] args) {

        // 引用泛型
        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("小黄", 1));
        dogs.add(new Dog("大白", 2));
        dogs.add(new Dog("小牛", 3));

        // 混入一只cat 此时无法混入，因为dogs约束了添加元素必须为Dog对象
        // dogs.add(new Cat("花小猫", 1, "我是一只猫"));

    }


    // 传统方式引入
    public static void test01() {
        // 传统方式引入
        ArrayList dogs = new ArrayList();
        dogs.add(new Dog("小黄", 1));
        dogs.add(new Dog("大白", 2));
        dogs.add(new Dog("小牛", 3));

        // 混入一只cat
        dogs.add(new Cat("花小猫", 1, "我是一只猫"));

        // 方式1：遍历数据（由于实体重写了toString，此处打印出来的是对象的toString，不需要额外转化）
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i));
        }

        System.out.println("---------------- 分割线 ----------------");
        // 方式2：假设默认dogs里面都是Dog类型，并不知道混进去一只Cat
        for (Object dog : dogs) {
            // 向下转型：Object=》Dog
            Dog d = (Dog) dog;
            System.out.println(d);
        }
    }


}


class Dog {

    public String name;
    public int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

// 猫定义
class Cat {

    public String name;
    public int age;
    public String descr;

    public Cat(String name, int age, String descr) {
        this.name = name;
        this.age = age;
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", descr='" + descr + '\'' +
                '}';
    }
}
