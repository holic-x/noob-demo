package com.noob.base.mq.service.impl;

import com.noob.base.mq.service.CountService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountServiceImpl implements CountService {
    @Override
    public void incrManyTimes(int num) {
        // 执行原子加操作
        AtomicInteger atomicInteger = new AtomicInteger();
        int res = 0;
        for (int i = 0; i < num; i++) {
            // 原子 + 1
            res = atomicInteger.incrementAndGet();
        }
        // 模拟复杂业务逻辑执行时间（沉睡10s）
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 输出结果
        System.out.println("最终执行结果：" + res);
    }
}
