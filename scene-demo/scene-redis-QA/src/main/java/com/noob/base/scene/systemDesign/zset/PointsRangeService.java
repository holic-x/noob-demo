package com.noob.base.scene.systemDesign.zset;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

/**
 * Zset 应用场景①：积分排名
 */
@Service
public class PointsRangeService {

    // Redis key：积分排名榜
    private static final String RANKING_KEY = "user:points";

    @Autowired
    private RedisTemplate redisTemplate;

    // 插入或者更新用户余额
    private void updateUserPoints(String userId, double amount) {
        redisTemplate.opsForZSet().add(RANKING_KEY, userId, amount);
    }

    // 获取用户当前金额
    public Double getUserPoints(String userId) {
        return redisTemplate.opsForZSet().score(RANKING_KEY, userId);
    }

    // 获取排行榜前N的用户(获取前10个积分的降序排名)
    public Set<ZSetOperations.TypedTuple<String>> getTopUsers(int n) {
        return redisTemplate.opsForZSet().reverseRangeByScore(RANKING_KEY, 0, n - 1);
    }

}