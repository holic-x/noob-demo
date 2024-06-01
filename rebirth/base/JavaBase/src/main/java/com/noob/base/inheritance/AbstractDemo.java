package com.noob.base.inheritance;


// Person 抽象类
abstract class Person{
    // 定义一个抽象方法sayHello
    public abstract void sayHello();
}

// 构建不同的人种继承Person，重写sayHello方法内容
class Chinese extends Person{
    @Override
    public void sayHello() {
        System.out.println("你好");
    }
}

class American extends Person{
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}

/**
 * 抽象方法demo
 */
public class AbstractDemo {
    public static void main(String[] args) {
        Chinese chinese = new Chinese();
        chinese.sayHello();
        American american = new American();
        american.sayHello();
    }
}
