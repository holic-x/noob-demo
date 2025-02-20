package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者、消费者模式
 */
public class ProducerConsumerWithLock {

    // 定义缓冲区和计数器
    static int BUFFER_SIZE = 10; // 限定缓冲区大小为10
    static int count = 0; // 当前缓冲区元素个数
    static Object[] buffer = new Object[BUFFER_SIZE]; // 定义缓冲区

    // 定义索引位置
    static int in = 0; // 下一个将元素放入缓冲区的索引位置
    static int out = 0;// 下一个要从缓冲区中取出元素的索引位置

    // 定义锁
    static ReentrantLock lock = new ReentrantLock();

    // 多Condition
    static Condition waitNotFull = lock.newCondition(); // 生产者等待缓冲区不满
    static Condition waitNotEmpty = lock.newCondition(); // 消费者等待缓冲区不为空

    // 定义生产者类（生产者线程）
    static class Producer extends Thread{
        @SneakyThrows
        @Override
        public void run() {
             // 生产者不断尝试生产数据
            while(true){
                lock.lock();

                    // 如果缓冲区满则阻塞生产
                    while(count==BUFFER_SIZE){ // 当前缓冲区数据个数达到缓冲区大小（此处不用in判断）
                        System.out.println("当前缓冲区已满，停止生产...");
                        waitNotFull.await();
                    }

                        // 如果缓冲区有空位，则生产数据并唤醒在等待的消费者线程
                        int num = new Random().nextInt(10000);
                        buffer[in] = num; // 生产一个随机数据
                        in = (in+1) % BUFFER_SIZE; // in 指针指向下一个位置
                        count++; // 元素个数+1
                        System.out.println("生产者生产了一个数据" + num);

                        // 唤醒其他线程
                        waitNotEmpty.signal();


                    // 模拟生产数据耗时
                    Thread.sleep(1000);

                    lock.unlock();
                }
        }
    }

    // 定义消费者类（消费者线程）
    static class Consumer extends Thread{
        @SneakyThrows
        @Override
        public void run() {
            // 消费者不断尝试消费数据
            while(true){
                lock.lock();

                    // 如果缓冲区空则阻塞消费
                    while(count==0){
                        System.out.println("当前缓冲区已空，停止消费....");
                        waitNotEmpty.await();
                    }
                        // 如果缓冲区有数据可用，则消费数据并唤醒在等待的生产者线程
                        int num = (int)buffer[out]; // 取出数据
                        out = (out+1) % BUFFER_SIZE; // out 指针指向下一个位置
                        count--; // 元素个数-1
                        System.out.println("消费者消费了一个数据" + num);
                        // 唤醒其他线程
                        waitNotFull.signal();


                    // 模拟消费数据耗时
                    Thread.sleep(1000);

                    lock.unlock();

                    }
        }
    }

    public static void main(String[] args) {
        // 测试
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start(); // 启动生产者线程
        consumer.start(); // 启动消费者线程
    }

}
