package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 053 最大子数组和
 */
public class Solution053_01 {

    // 动态规划思路
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        // 1.dp构建：dp[i]表示以i位置结尾的元素的连续子数组的最大和
        int[] dp = new int[len];

        /**
         * 2.dp递归
         * - 计算连续子数组和，取最大
         * - dp[i] = max{dp[i-1]+nums[i],nums[i]}(要么继续拼接以i位置元素结尾，要么另起门户)
         */

        // 3.dp初始化
        dp[0] = Math.max(Integer.MIN_VALUE, nums[0]);

        // 4.dp构建
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        // 返回结果值
        // return max == Integer.MIN_VALUE ? 1 : max;
        return max;
    }
}
