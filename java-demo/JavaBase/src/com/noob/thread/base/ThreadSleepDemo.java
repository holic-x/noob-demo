package com.noob.thread.base;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadSleepDemo {
    /**
     *	线程休眠:
     *	让当前线程休眠一定的时间，但线程始终不会放弃CPU的执行权，
     *	等待休眠结束后，继续获得CPU的执行权，且在sleep休眠期间
     *  其它线程不会获得CPU的执行权
     */
    public static void main(String[] args) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<100;i++)
                {
                    // 实现每打印一个随机数据休眠一秒
                    try {
                        System.out.println((int)Math.random()*100);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
