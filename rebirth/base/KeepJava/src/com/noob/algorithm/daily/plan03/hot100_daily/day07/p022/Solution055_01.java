package com.noob.algorithm.daily.plan03.hot100_daily.day07.p022;

/**
 * 🟡 055 跳跃游戏 - https://leetcode.cn/problems/jump-game/description/
 */
public class Solution055_01 {

    /**
     * 思路分析：
     * 在有效的覆盖范围内更新最远可到达的位置
     */
    public boolean canJump(int[] nums) {
        // 可支撑的最大里程
        int maxCover = nums[0];

        for (int i = 0; i < nums.length; i++) {
            // 1.可达性校验：判断当前的最大里程是否可以支持走到当前节点
            if (maxCover < i) {
                return false; // 如果位置不可达则返回false
            }

            // 2.更新最大的覆盖范围：如果当前位置可达，则更新当前可跳跃的最大位置(跳跃游戏不是油量累加，每一跳只依赖于当前nums[i]，其可覆盖的范围是i+nums[i])
            maxCover = Math.max(maxCover, i + nums[i]);

            // 3.提前检查是否可达终点(非必须)
            if (maxCover >= nums.length - 1) {
                return true;
            }
        }
        // 校验通过
        return true;
    }

}
