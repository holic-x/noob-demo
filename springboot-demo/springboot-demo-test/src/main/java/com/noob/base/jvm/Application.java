package com.noob.base.jvm;

/**
 * main 方法执行过程分析
 */
public class Application {
    public static void main(String[] args) {
        Person p = new Person("noob");
        p.getName();
    }
}

class Person{
    public String name;

    public Person(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
