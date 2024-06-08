package com.noob.framework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class PayService {

    private static final Logger log = LoggerFactory.getLogger(PayService.class);

    private final int totalNum = 100000;

    // 设置retry规则
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public int minGoodsnum(int num) throws Exception {
        log.info("减库存开始:{}" , LocalTime.now());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("illegal");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("指定数量不正确");
        }
        log.info("减库存执行结束:{}" , LocalTime.now());
        return totalNum - num;
    }

}
