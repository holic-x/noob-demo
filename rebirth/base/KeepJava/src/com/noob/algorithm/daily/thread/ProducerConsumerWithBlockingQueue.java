package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于BlockingQueue实现生产者、消费者模式
 */
public class ProducerConsumerWithBlockingQueue {
    // 创建一个容量为 10 的阻塞队列
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    @SneakyThrows
    // 生产方法
    public void produce() {
        for (int i = 0; i < 20; i++) {
            queue.put(i);  // 生产数据
            System.out.println("生产者生产: " + i);
            Thread.sleep(1000);  // 模拟生产耗时
        }
    }

    @SneakyThrows
    // 消费方法
    public void consume() {
        for (int i = 0; i < 20; i++) {
            int value = queue.take();  // 消费数据
            System.out.println("消费者消费: " + value);
            Thread.sleep(2000);  // 模拟消费耗时
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ProducerConsumerWithBlockingQueue test = new ProducerConsumerWithBlockingQueue();

        // 创建生产者线程
        Thread producer = new Thread(() -> {
            test.produce();
        });

        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            test.consume();
        });

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();

        // 等待线程结束
        producer.join();
        consumer.join();

        System.out.println("生产者和消费者任务完成！");
    }
}