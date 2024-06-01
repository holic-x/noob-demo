package com.noob.multiThread.cas;

import java.util.concurrent.atomic.AtomicReference;

class Person{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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
}


// 原子更新引用类型
public class AtomicReferenceDemo {

    public static AtomicReference<Person> atomicUserRef = new AtomicReference<Person>();

    public static void main(String[] args) {
        // 定义原子更新引用对象
        Person p = null;
        Person p1 = new Person("P1",12);
        atomicUserRef.set(p1);
        Person p2 = new Person("P2",18);
        // 比较当前引用对象和预期对象是否一致，如果是同一个对象则更新
        atomicUserRef.compareAndSet(p, p2);
        // 打印结果
        Person resPerson = atomicUserRef.get();
        System.out.println(resPerson.getName() + "-" + resPerson.getAge());
    }
}
