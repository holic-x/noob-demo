package com.noob.base.inheritance;

interface A{
    public void say();
}

interface B{
    public void eat();
}

class C implements A,B{

    // 实现A的方法定义
    @Override
    public void say() {
        System.out.println("say hello");
    }

    // 实现B的方法定义
    @Override
    public void eat() {
        System.out.println("eating");
    }
}

/**
 * implements 关键字变相实现"多继承"
 */
public class ImpDemo {
    public static void main(String[] args) {
        C c = new C();
        c.say();
        c.eat();
    }
}
