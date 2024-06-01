package com.noob.multiThread.lcokDemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 1.乐观锁 VS 悲观锁
class OPLock{

    // ---------- 悲观锁：synchronized、Lock -----------
    public synchronized void testSynchronized(){
        System.out.println("普通实例方法");
    }

    public void testLock(){
        Lock lock = new ReentrantLock();
        // 使用前加锁
        lock.lock();
        try{
            System.out.println("do sth");
        }finally {
            // 解锁
            lock.unlock();
        }
    }

    // ---------- 乐观锁：AtomicInteger ----------
    private AtomicInteger atomicInteger = new AtomicInteger();  // 需要保证多个线程使用的是同一个AtomicInteger
    public void testAtomic(){
        atomicInteger.incrementAndGet(); //执行自增1
    }
}



/**
 * 常见锁分类demo
 */
public class LockClassifyDemo {
}
