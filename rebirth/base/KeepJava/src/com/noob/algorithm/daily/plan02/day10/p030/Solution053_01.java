package com.noob.algorithm.daily.plan02.day10.p030;

import java.util.Arrays;

/**
 * 🟡 053 最大子数组和
 */
public class Solution053_01 {

    /**
     * 思路分析：动态规划
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 1.dp 定义：dp[i] 表示以i位置元素结尾的最大子数组和
        int[] dp = new int[n];

        /**
         * 2.dp 递推
         * dp[i] = max{dp[i-1]+nums[i],nums[i]}
         */

        // 3.dp 初始化
        dp[0] = nums[0];
        int maxVal = dp[0];

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxVal = Math.max(maxVal, dp[i]);
        }

        // 返回结果
        return maxVal;
    }
}
