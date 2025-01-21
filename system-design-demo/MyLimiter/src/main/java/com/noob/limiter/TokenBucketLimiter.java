package com.noob.limiter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限流算法：令牌桶限流
 */
public class TokenBucketLimiter {
    private final int rate; // 令牌产生的速度，即每秒生成多少个令牌
    private final int capacity; // 令牌桶容量，最多可存储令牌数
    private int currentTokenNum; // 当前令牌数
    private LocalDateTime lastTime; // 上次请求时间
    private final Lock lock; // 请求锁

    public TokenBucketLimiter(int rate, int capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.currentTokenNum = 0;
        this.lastTime = LocalDateTime.now();
        this.lock = new ReentrantLock();
    }

    public int getCurrentTokenNum() {
        return currentTokenNum;
    }

    public int getRate() {
        return rate;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean allow() {
        lock.lock();
        try {
            long millisSinceLast = ChronoUnit.MILLIS.between(lastTime, LocalDateTime.now());
            int tokenCount = (int) (millisSinceLast / 1000.0 * rate);

            if (tokenCount > 0) {
                currentTokenNum += tokenCount;
                lastTime = LocalDateTime.now();
            }

            if (currentTokenNum > capacity) {
                currentTokenNum = capacity;
            }

            if (currentTokenNum > 0) {
                currentTokenNum--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public static void mockRequest(int n, Duration d, TokenBucketLimiter limiter) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        for (int i = 0; i < n; i++) {
            int requestId = i + 1;
            executor.schedule(() -> {
                if (limiter.allow()) {
                    System.out.printf("第%d个请求通过\n", requestId);
                } else {
                    System.out.printf("第%d个请求被限流\n", requestId);
                }
            }, i * d.toMillis(), TimeUnit.MILLISECONDS);
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public static void main(String[] args) {
        System.out.println("=================令牌桶算法=================");
        // 创建一个令牌桶，令牌的生成速度为每秒4个，桶容量为5个请求
        TokenBucketLimiter limiter = new TokenBucketLimiter(4, 5);
        // 发送10个请求，每50ms发送一个
        mockRequest(10, Duration.ofMillis(50), limiter);
        System.out.println("------------------------------------------");
    }
}
