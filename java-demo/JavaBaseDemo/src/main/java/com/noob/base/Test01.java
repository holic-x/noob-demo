package com.noob.base;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 7:52
 */
public class Test01 {
    public static void main(String[] args) {
        Person a = new Person();
        a.age = 10;
        a.name = "小明";
        Person b;
        b = a;
        System.out.println(b.name); // 小明
        b.age = 200;
        b = null;
        System.out.println(a.age);  //
        System.out.println(b.age); //
    }
}
class Person {
    public String name;
    public int age;
}
