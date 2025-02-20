package com.noob.algorithm.daily.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithReentrantLock {
    private static final int BUFFER_SIZE = 10;
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition(); // 缓冲区未满的条件
    private final Condition notEmpty = lock.newCondition(); // 缓冲区非空的条件

    public static void main(String[] args) {
        ProducerConsumerWithReentrantLock pc = new ProducerConsumerWithReentrantLock();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            lock.lock(); // 加锁
            try {
                // 如果缓冲区已满，等待
                while (buffer.size() == BUFFER_SIZE) {
                    notFull.await(); // 等待缓冲区未满的条件
                }

                System.out.println("生产者生产: " + value);
                buffer.add(value++);

                notEmpty.signal(); // 唤醒等待缓冲区非空的消费者
            } finally {
                lock.unlock(); // 释放锁
            }

            // 为了演示，减慢生产速度
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            lock.lock(); // 加锁
            try {
                // 如果缓冲区为空，等待
                while (buffer.isEmpty()) {
                    notEmpty.await(); // 等待缓冲区非空的条件
                }

                int value = buffer.poll();
                System.out.println("消费者消费: " + value);

                notFull.signal(); // 唤醒等待缓冲区未满的生产者
            } finally {
                lock.unlock(); // 释放锁
            }

            // 为了演示，减慢消费速度
            Thread.sleep(100000);
        }
    }
}

// todo 生产消费速率差异太大会导致长期满、空两极状态....