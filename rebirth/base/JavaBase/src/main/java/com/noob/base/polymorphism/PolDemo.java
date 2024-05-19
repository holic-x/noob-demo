package com.noob.base.polymorphism;

// 父类定义
class Car{
    public void run(){
        System.out.println("car run");
    }
}

// 子类继承父类，并重写父类方法
class Bmw extends Car{
    @Override
    public void run(){
        System.out.println("bmw run");
    }
}

class Banz extends Car{
    @Override
    public void run(){
        System.out.println("banz run");
    }
}

/**
 * 多态案例
 */
public class PolDemo {
    public static void main(String[] args) {
        // 父类引用指向子类对象（父类声明）
        Car[] cars = {new Car(),new Bmw(), new Banz()};
        for (Car car : cars) {
            car.run();
        }
    }
}
