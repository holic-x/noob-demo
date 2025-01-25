package com.noob.limiter;

import java.util.concurrent.locks.ReentrantLock;
import java.time.Duration;
import java.time.Instant;

/**
 * 限流算法：漏斗限流
 */
public class LeakyBucketLimiter {
    private int rate;
    private int capacity;
    private int currentReqNum;
    private Instant lastTime;
    private final ReentrantLock lock = new ReentrantLock();

    public LeakyBucketLimiter(int rate, int capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.currentReqNum = 0;
        this.lastTime = Instant.now();
    }

    public boolean allow() {
        lock.lock();
        try {
            long elapsed = Duration.between(lastTime, Instant.now()).toMillis();
            double seconds = elapsed / 1000.0;
            int leakyReqCount = (int) (seconds * rate);

            if (leakyReqCount > 0) {
                currentReqNum -= leakyReqCount;
                lastTime = Instant.now();
            }

            if (currentReqNum < 0) {
                currentReqNum = 0;
            }

            if (currentReqNum < capacity) {
                currentReqNum++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public static void mockRequest(int n, long delay, LeakyBucketLimiter limiter) {
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (limiter.allow()) {
                System.out.printf("第%d个请求通过\n", i + 1);
            } else {
                System.out.printf("第%d个请求被限流\n", i + 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("=================漏桶算法=================");
        LeakyBucketLimiter limiter = new LeakyBucketLimiter(4, 5);
        mockRequest(10, 50, limiter);
        System.out.println("------------------------------------------");
    }
}




