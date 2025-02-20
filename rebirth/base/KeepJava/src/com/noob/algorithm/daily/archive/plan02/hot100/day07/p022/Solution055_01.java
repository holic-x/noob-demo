package com.noob.algorithm.daily.archive.plan02.hot100.day07.p022;

/**
 * 🟡 055 跳跃游戏 - https://leetcode.cn/problems/jump-game/description/
 */
public class Solution055_01 {

    /**
     * 思路分析：判断是否可以到达nums的最后1个下标（nums[i]表示在该位置可跳跃的最大长度）
     * 贪心思路：在有效的覆盖范围内更新最大覆盖范围
     */
    public boolean canJump(int[] nums) {
        int maxCover = 0;
        for (int i = 0; i <= maxCover; i++) {
            // 更新当前的最大覆盖范围
            maxCover = Math.max(maxCover, i + nums[i]); // 已走路径+可跳步数=目前可覆盖的最大距离
            // 可在更新的过程中判断最大覆盖举例可否支持走到末尾
            if (maxCover >= nums.length - 1) {
                return true;
            }
        }
        // 无法到达
        return false;
    }

}
