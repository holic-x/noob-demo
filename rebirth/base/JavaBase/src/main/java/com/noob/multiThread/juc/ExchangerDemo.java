package com.noob.multiThread.juc;

import java.util.concurrent.Exchanger;

// 线程间数据交换Exchanger
public class ExchangerDemo {

    public static void main(String[] args) {
         final Exchanger<String> exchanger = new Exchanger();
         // 构建线程A
         new Thread(()->{
             try {
                 String send = "你好 我是AAA";
                 String receive = exchanger.exchange(send);
                 System.out.println(Thread.currentThread().getName() + "接收消息:" + receive);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         },"A").start();

        // 构建线程B
        new Thread(()->{
            try {
                String send = "你好 我是BBB";
                String receive = exchanger.exchange(send);
                System.out.println(Thread.currentThread().getName() + "接收消息:" + receive);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"B").start();
    }
}
