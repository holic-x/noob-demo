package com.noob.java8.lambda;

interface MyInterface{
    // 抽象方法
    void doSth(String value);

    // 默认方法
    default void print(){
        System.out.println("hello default print");
    }

    // 静态方法
    static void show(){
        System.out.println("hello static show");
    }
}

/**
 * Lambda表达式案例
 */
public class LambdaDemo {
    public static void main(String[] args) {
        // 1.传统方式实现接口
        new MyInterface() {
             @Override
             public void doSth( String value) {
                 System.out.println("传统方式 doSth:" + value);
             }
         }.doSth("冲冲冲");

        // 2.Lambda表达式
        MyInterface myInterface = (val) -> {
            System.out.println("Lambda doSth:" + val);
        };
        myInterface.doSth("hello");
    }
}
