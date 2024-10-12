package com.noob.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 交叉打印12A34B56C.....
 */
public class Demo51 {

    // 思路：回归双线程打印思路（一个打印数字、一个打印字母）
    static AtomicInteger counter = new AtomicInteger(1); // 定义计数器
    static Object obj = new Object(); // 定义对象锁
    static int mark = 1; // 定义打印标识（打印轮次：1-数字，2-字母）

    // 定义字母打印计数器
    static AtomicInteger letterCounter = new AtomicInteger(1); // 定义计数器

    public static void main(String[] args) {
        // 线程A 打印数字
        Thread ta = new Thread(()->{
            while(counter.get() < 52) { // 字母有26位，此处数字打印到52即可
                // 加锁区域
                synchronized (obj) {
                    if(mark==1){
                        // 数字打印两下
                        System.out.print(counter.getAndIncrement());
                        System.out.print(counter.getAndIncrement());
                        // 切换标识
                        mark = 2;
                    }
                }
            }
        });

        // 线程B 打印字母
        Thread tb = new Thread(()->{
            while(letterCounter.get() <= 26) { // 打印字母
                // 加锁区域
                synchronized (obj) {
                    if(mark==2){
                        // 打印字母(可以将对应计数器转为字母符号，也可以自定义字母词典通过计数器作为下标获取字母符号)
//                        System.out.print((char)(letterCounter.getAndIncrement() + 'a' -1)); // 此处将数字转为对应的字母
                        System.out.print(Character.toUpperCase((char)(letterCounter.getAndIncrement() + 'a' -1))); // 此处将数字转为对应的字母
                        // 切换标识
                        mark = 1;
                    }
                }
            }
        });

        // 启动线程
        ta.start();
        tb.start();
    }
}
