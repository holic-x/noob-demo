package com.noob.algorithm.daily.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 10;
    private final Queue<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

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
            synchronized (this) {
                // 如果缓冲区已满，等待消费者消费
                while (buffer.size() == BUFFER_SIZE) {
                    wait();
                }

                System.out.println("生产者生产: " + value);
                buffer.add(value++);

                // 通知消费者可以消费了
                notify();

                // 为了演示，减慢生产速度
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // 如果缓冲区为空，等待生产者生产
                while (buffer.isEmpty()) {
                    wait();
                }

                int value = buffer.poll();
                System.out.println("消费者消费: " + value);

                // 通知生产者可以生产了
                notify();

                // 为了演示，减慢消费速度
                Thread.sleep(1000);
            }
        }
    }
}