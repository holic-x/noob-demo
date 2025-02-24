package com.noob.algorithm.plan_archive.plan01.day37;

import java.util.Arrays;

/**
 * 🟡 053 最大子数组和 - https://leetcode.cn/problems/maximum-subarray/description/
 */
public class Solution053_01 {

    /**
     * 思路分析：求一个整数数组nums的最大和的连续子数组
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        // 特例判断
        if (n == 1) {
            return nums[0];
        }

        // 1.dp 定义：dp[i] 表示以i位置元素结尾的最大连续子数组和
        int[] dp = new int[n];

        /**
         * 2.dp 递推：
         * 基于dp[i]的定义，要么继续拼接上一个连续子数组，要么自成一派,从两者中选择max的情况
         * dp[i] = max{dp[i-1] + nums[i],nums[i]}
         */

        // 3.dp 初始化
        Arrays.fill(dp, Integer.MIN_VALUE); // 每个元素自身可以作为一个子数组，此处初始化为最小值
        dp[0] = nums[0]; // 第一个元素初始化为自身

        // 4.dp 构建
        int maxVal = dp[0]; // 注意初始化max的值
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // 分情况讨论
            maxVal = Math.max(maxVal, dp[i]);
        }

        // 返回结果
        return maxVal;
    }
}
