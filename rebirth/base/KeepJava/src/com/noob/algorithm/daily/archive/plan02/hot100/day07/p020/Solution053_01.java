package com.noob.algorithm.daily.archive.plan02.hot100.day07.p020;

/**
 * 🟡 053 最大子数组和 - https://leetcode.cn/problems/maximum-subarray/description/
 */
public class Solution053_01 {

    /**
     * 思路分析：最大子数组和（具有最大和的连续子数组）
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 1.dp 定义(dp[i]表示以i位置结尾的子数组的最大和)
        int[] dp = new int[n];

        /**
         * 2.dp 递推
         * dp[i] 的取值有两种情况,从这两种情况中选择max
         * - ① nums[i]拼接在前面的子数组中得到最大和 => dp[i-1] + nums[i]
         * - ② nums[i]无法拼接，自成一派 => nums[i]
         */

        // 3.dp 初始化
        dp[0] = nums[0];

        // 4.dp 构建（遍历顺序）
        int maxVal = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxVal = Math.max(maxVal, dp[i]);
        }

        // 返回结果
        return maxVal;
    }
}
