package com.noob.base.scene.systemDesign.bitmap;

import cn.hutool.core.date.DateUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Bitmap 场景应用①：打卡功能
 */
@Service
public class SignService {

    // Redis 中打卡记录的 key 前缀
    private static final String USER_SIGN_KEY = "user:sign:";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户打卡：基于用户维度存储
     * @param userId 用户ID
     */
    public void sign(Long userId) {
        // 1. 获取当前日期
        Date now = new Date();
        // 2. 生成 Redis 的 key（格式：user:sign:{userId}:{yyyyMM}）
        String key = USER_SIGN_KEY + userId + ":" + DateUtil.format(now, "yyyyMM");
        // 3. 获取当前是当月的第几天（从 1 开始）
        int dayOfMonth = DateUtil.dayOfMonth(now);
        // 4. 记录打卡：将当月第 N 天的位标记为 1（offset 从 0 开始，所以减 1）
        redisTemplate.opsForValue().setBit(key, dayOfMonth - 1, true);
    }
}