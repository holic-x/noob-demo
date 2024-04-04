package com.noob.thread.safe;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/26
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class DeathLockDemo {
    public static void main(String[] args) {
        Object objA = new Object();
        Object objB = new Object();

        new Thread(()->{
            while(true){
                synchronized (objA){
                    //线程一
                    synchronized (objB){
                        System.out.println("BBBBBB");
                    }
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                synchronized (objB){
                    //线程二
                    synchronized (objA){
                        System.out.println("AAAAAA");
                    }
                }
            }
        }).start();
    }
}
