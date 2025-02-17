package com.noob.algorithm.daily.plan02.day07.p022;

/**
 * 🟡 055 跳跃游戏 - https://leetcode.cn/problems/jump-game/description/
 */
public class Solution055_02 {

    /**
     * 思路分析：判断是否可以到达nums的最后1个下标（nums[i]表示在该位置可跳跃的最大长度）
     * 贪心思路：在有效的覆盖范围内更新最大覆盖范围
     */
    public boolean canJump(int[] nums) {
        int maxCover = 0;
        for (int i = 0; i < nums.length; i++) {
            // 校验是否可跳跃到当前位置
            if (maxCover < i) {
                return false; // 无法走到当前节点
            }
            maxCover = Math.max(maxCover, i + nums[i]);
        }
        return true;
    }

}
