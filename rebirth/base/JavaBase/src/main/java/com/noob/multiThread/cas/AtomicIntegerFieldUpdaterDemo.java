package com.noob.multiThread.cas;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class User{
    private String name;
    public volatile int age;
    public User(String name, int age) {
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



// 原子更新字段类型 demo
public class AtomicIntegerFieldUpdaterDemo {

    private static AtomicIntegerFieldUpdater<User> aifu = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {
        User user = new User("noob",18);
        System.out.println(aifu.getAndIncrement(user));
        System.out.println(aifu.get(user));
    }
}
