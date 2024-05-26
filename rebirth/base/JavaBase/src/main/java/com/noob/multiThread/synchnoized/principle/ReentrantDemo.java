package com.noob.multiThread.synchnoized.principle;

// 2.可重入的实现原理
public class ReentrantDemo {

    private synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + ":method1()");
        method2();
    }

    private synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + ":method2()");
        method3();
    }

    private synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + ":method3()");
    }

    public static void main(String[] args) {
        ReentrantDemo demo = new ReentrantDemo();
        demo.method1();
    }
}
