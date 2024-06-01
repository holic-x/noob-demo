package com.noob.multiThread.lcokDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockBaseDemo {
    public static void main(String[] args) {
        // 锁
        Lock lock = new ReentrantLock();
        // 需显式定义加锁和解锁
        lock.lock();
        try {
            // 业务逻辑
        }finally {
            lock.unlock();
        }

        // 读写锁
       ReadWriteLock rwLock = new ReentrantReadWriteLock();
    }
}
