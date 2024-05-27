package com.noob.multiThread.lcokDemo;

/**
 * 重入概念
 */
public class WidgetDemo {
    public synchronized void doSomething() {
        System.out.println("方法1执行...");
        doOthers();
    }

    public synchronized void doOthers() {
        System.out.println("方法2执行...");
    }

    public static void main(String[] args) {
        WidgetDemo demo = new WidgetDemo();
        demo.doSomething();
    }

}
