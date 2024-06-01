package com.noob.multiThread.volatileDemo.principle;

// 2.有序性的实现：happens-before规则
public class OrderDemo {
    int a = 0;
    volatile boolean flag = false;

    public void writer(){
        a  = 1;          // 1.线程A修改共享变量
        flag = true;     // 2.线程A写volatile变量
    }

    public void reader(){
        if(flag){        // 3.线程B读同一个volatile变量
            int i = a;   // 4.线程B读共享变量
        }
    }
}
