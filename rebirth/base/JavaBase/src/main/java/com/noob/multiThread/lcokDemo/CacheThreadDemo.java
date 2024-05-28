package com.noob.multiThread.lcokDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 模拟Cache实现：利用读写锁来保证并发状态下对应Cache的读get、写out、清除clear操作的安全性
public class CacheThreadDemo {

    // 定义Map存储数据
    static Map<String,Object> map = new HashMap<>();

    // 定义读写锁确保并发状态下map的读写安全
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    static Lock readLock = rwl.readLock();
    static Lock writeLock = rwl.writeLock();

    // 读：获取key对应的value
    public static final Object get(String key) {
        // 加锁
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            // 解锁
            readLock.unlock();
        }
    }

    // 写：设置指定key的value，并返回旧value
    public static final Object put(String key,Object value) {
        // 加锁
        writeLock.lock();
        try{
            return map.put(key,value);
        }finally {
            // 解锁
            writeLock.unlock();
        }
    }

    // 清理所有的内容
    public static final void clear(){
        // 加锁
        writeLock.lock();
        try{
            map.clear();
        }finally {
            // 解锁
            writeLock.unlock();
        }
    }
}
