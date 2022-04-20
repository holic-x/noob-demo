package com.noob.thread.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        // 1.volatile非原子操作:不同对象操作公共的static变量
        VolatileNoAtomic[] arr =new VolatileNoAtomic[10];
        for(int i=0;i<arr.length;i++) {
            arr[i]=new VolatileNoAtomic();
        }
        for(int i=0;i<10;i++) {
            arr[i].start();
        }

        // 2.volatile+synchronized:操作同一个对象的成员变量
        VolatileAtomic va = new VolatileAtomic();
        for(int i=0;i<10;i++) {
            new Thread(va).start();
        }

        // 3.借助AtomicInteger实现
        AtomicOper[] arr3 =new AtomicOper[10];
        for(int i=0;i<arr3.length;i++) {
            arr3[i]=new AtomicOper();
        }
        for(int i=0;i<10;i++) {
            arr3[i].start();
        }
    }
}

// demo1:volatile无法保证原子性
class VolatileNoAtomic extends Thread{
    private static volatile int count ;
    public static  void addCount() {
        for(int i=0;i<10000;i++) {
			count++;
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }
}

// demo2:借助synchronized加锁保证原子性
class VolatileAtomic implements Runnable {
    private volatile int count ;
    // 定义对象锁
    private Object lock = new Object();

    public void addCount() {
        for(int i=0;i<10000;i++) {
            /**
             * 多线程场景下volatile关键字修饰的变量情况
             * 1.从共享数据中读取数据到本线程栈中
             * 2.修改本线程栈中变量副本的值
             * 3.将本线程栈中变量副本的值赋值给共享数据
             */
            synchronized (lock){
                count++;
            }
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }
}

// demo3:借助AtomicInteger实现原子操作
class AtomicOper extends Thread{

    private static AtomicInteger count =new AtomicInteger(0);

    public static  void addCount() {
        for(int i=0;i<10000;i++) {
            count.incrementAndGet();
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }
}