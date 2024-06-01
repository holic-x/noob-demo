package com.noob.multiThread.synchronizedDemo.principle;

// 3.保证可见性的原理-锁的内存语义
public class VisibilityDemo {

    private int a=0;

    public synchronized void writer(){
        a++;
    }
    public synchronized void reader(){
        int i=a;
    }

}
