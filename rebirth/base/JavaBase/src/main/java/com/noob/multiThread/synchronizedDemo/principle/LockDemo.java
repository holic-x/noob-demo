package com.noob.multiThread.synchronizedDemo.principle;

// 1.加锁和释放锁的原理
public class LockDemo implements Runnable{

    @Override
    public void run() {
        // 同步方法块
        synchronized (this){
            System.out.println("同步方法块：观察synchronized生成的指令");
        }
    }

    // 同步静态方法
    public static synchronized void method(){
        System.out.println("同步普通方法");
    }
}


