package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 生产者、消费者模式 - 基于 synchronized 实现
 */
public class ProducerConsumerWithSyncAndQueu2 {

    static int BUFFER_SIZE = 10; // 设置缓冲区大小（上限）
    static Queue<Integer> buffer = new LinkedList<>(); // 定义缓冲区
    static Object lock = new Object(); // 定义锁（对象锁）

    @SneakyThrows
    // 定义生产方法
    public void produce() {
        while (true) {
            synchronized (lock) {
                while (buffer.size() == BUFFER_SIZE) {
                    System.out.println("当前缓冲区满，停止生产......");
                    // 缓冲区满，阻塞生产者线程
                    lock.wait();
                }
                // 缓冲区未满，随机生产数据
                int num = new Random().nextInt(100);
                buffer.add(num);
                System.out.println("生产者生产一个数据：" + num);
                // 唤醒可能在等待的消费者线程
                lock.notify();
                // 模拟生产时耗
                Thread.sleep(10);
            }
        }
    }

    @SneakyThrows
    // 定义消费方法
    public void consume() {
        while (true) {
            synchronized (lock) {
                // 当缓冲区为空，停止消费
                while (buffer.size() == 0) {
                    lock.wait();
                }
                // 缓冲区不为空则继续消费数据
                int getNum = buffer.poll();
                System.out.println("消费者取出一个数据：" + getNum);
                // 唤起其他可能在等待的生产者
                lock.notify();
            }
            // 模拟消费耗时
            Thread.sleep(1000);
        }
    }


    public static void main(String[] args) {
        // 测试
        ProducerConsumerWithSyncAndQueu2 test = new ProducerConsumerWithSyncAndQueu2();

        // 定义生产者线程
        Thread producer = new Thread(() -> {
            test.produce(); // 调用生产者方法
        });

        // 定义消费者线程
        Thread consumer = new Thread(() -> {
            test.consume(); // 调用消费者方法
        });

        // 启动线程
        producer.start();
        consumer.start();
    }

}
