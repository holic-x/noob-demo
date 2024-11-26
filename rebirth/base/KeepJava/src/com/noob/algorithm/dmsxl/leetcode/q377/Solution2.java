package com.noob.algorithm.dmsxl.leetcode.q377;

/**
 * 377 组合总和IV
 */
public class Solution2 {
    // 动态规划（一维数组版本）
    public int combinationSum4(int[] nums, int target) {
        int m = nums.length;
        // 1.dp[j] 构成容量为j的组合方案
        int[] dp = new int[target + 1];

        /**
         * 2.dp[] 推导
         * j<nums[i] dp[j]=dp[j]
         * j>=nums[i] dp[j]+=dp[j-nums[i]] // 加上当前元素`i`的构成方案
         */

        // 3.dp初始化
        dp[0] = 1; // 构成容量为0的有1种组合方案

        // 4.构建dp
        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < m; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }

        // 返回结果
        return dp[target];
    }
}
