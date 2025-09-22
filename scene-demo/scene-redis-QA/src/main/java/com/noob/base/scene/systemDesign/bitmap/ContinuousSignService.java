package com.noob.base.scene.systemDesign.bitmap;

import cn.hutool.core.date.DateUtil;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Bitmap 场景应用②：连续登录功能
 */
@Service
public class ContinuousSignService {

    private static final String USER_SIGN_KEY = "user:sign:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate; // 修正泛型为<Object>

    /**
     * 查询用户连续登录天数
     *
     * @param userId 用户ID
     * @return 连续登录天数
     */
    public int getContinuousSignDays(Long userId) {
        // 1. 获取当前日期和当月 key
        Date now = new Date();
        String key = USER_SIGN_KEY + userId + ":" + DateUtil.format(now, "yyyyMM");

        // 2. 检查 key 是否存在（避免空 key 操作）
        if (!redisTemplate.hasKey(key)) { // 更简单的判断方式
            return 0; // 当月无打卡记录，连续天数为 0
        }

        // 3. 获取当前是当月的第几天（从 1 开始）
        int dayOfMonth = DateUtil.dayOfMonth(now);

        // 4. 使用 BITFIELD 命令获取连续打卡位
        List<Long> result = redisTemplate.execute((RedisConnection connection) ->
                connection.bitField(
                        key.getBytes(),
                        BitFieldSubCommands.create()
                                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                                .valueAt(0)
                )
        );

        if (result == null || result.isEmpty() || result.get(0) == 0) {
            return 0; // 无连续打卡
        }

        Long bitFieldResult = result.get(0);

        // 5. 计算连续 1 的最长前缀长度（即连续登录天数）
        // 处理最后一位为0的情况（今天未打卡）
        String binaryStr = Long.toBinaryString(bitFieldResult);
        int continuousDays = 0;

        // 从右向左检查连续的1（因为二进制字符串最右侧是最早的日期）
        for (int i = binaryStr.length() - 1; i >= 0; i--) {
            if (binaryStr.charAt(i) == '1') {
                continuousDays++;
            } else {
                break;
            }
        }

        return continuousDays;
    }
}
