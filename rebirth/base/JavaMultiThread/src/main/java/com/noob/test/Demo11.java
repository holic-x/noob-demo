package com.noob.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 01.写一个双线程轮流打印1-100
 */
public class Demo11 {

    // 思路：对象锁（计数器+双线程轮流打印）
    static Object obj = new Object(); // 对象锁
    static AtomicInteger counter = new AtomicInteger(1); // 计数器
    static int mark = 1; // 打印标记（通过标记切换线程：mark=1表示线程A；mark为B表示线程B）

    public static void main(String[] args) {

        // 这种思路是到自己的轮次才执行，还有一种设计思路是while判断如果非自己的轮次则持续wait（持续阻塞），等到自己的轮次则执行并唤醒其他线程、切换标记（synchronized+wait/notify+打印标记）
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while(counter.get()<100){
                    // 加锁区域
                    synchronized (obj){
                        if(mark==1){
                            // 执行内容
                            System.out.println("线程A：" + counter.getAndIncrement());
                            // 执行完成后唤醒其他线程并切换打印标记，让其他线程有机会参与其中
                            mark = 2;
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while(counter.get()<100){
                    // 加锁区域
                    synchronized (obj){
                        if(mark==2){
                            // 执行内容
                            System.out.println("线程B：" + counter.getAndIncrement());
                            // 执行完成后唤醒其他线程并切换打印标记，让其他线程有机会参与其中
                            mark = 1;
                        }
                    }

                }
            }
        });

        // 启动线程进行测试
        threadA.start();
        threadB.start();

    }

}
