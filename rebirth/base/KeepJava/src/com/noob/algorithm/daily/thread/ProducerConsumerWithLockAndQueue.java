package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者、消费者模式
 */
public class ProducerConsumerWithLockAndQueue {

    static int BUFFER_SIZE = 10; // 限定缓冲区大小为10
    static Queue<Integer> buffer = new LinkedList<>(); // 定义缓冲区

    // 定义锁
    static ReentrantLock lock = new ReentrantLock();
    // 多Condition
    static Condition notFull = lock.newCondition(); // 缓冲区未满（表示生产者可以进行生产的条件）
    static Condition notEmpty = lock.newCondition(); // 缓冲区不为空（表示消费者可以进行消费的条件）

    @SneakyThrows
    // 定义生产方法
    public void produce() {
        while (true) {
            lock.lock();

            while (buffer.size() == BUFFER_SIZE) {
                System.out.println("当前缓冲区已满，停止生产...");
                notFull.await();
            }
            int num = new Random().nextInt(1000);
            System.out.println("生产者生产数据：" + num);
            buffer.add(num);

            // 唤醒可能正在等待的消费者线程
            notEmpty.signal();

            // 模拟生产耗时
            Thread.sleep(1000);

            lock.unlock();
        }
    }

    @SneakyThrows
    // 定义消费方法
    public void consume() {
        while (true) {
            lock.lock();

            while (buffer.size() == 0) {
                System.out.println("当前缓冲区已空，停止消费...");
                notEmpty.await();
            }
            int num = buffer.poll();
            System.out.println("消费者消费数据：" + num);

            // 唤醒可能正在等待的生产者线程
            notFull.signal();

            // 模拟消费耗时
            Thread.sleep(100);

            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 测试
        ProducerConsumerWithLockAndQueue test = new ProducerConsumerWithLockAndQueue();
        // 创建生产者线程
        Thread producer = new Thread(() -> {
            test.produce();
        });

        // 创建消费消费者线程
        Thread consumer = new Thread(() -> {
            test.consume();
        });

        // 启动线程
        producer.start();
        consumer.start();
    }

}
