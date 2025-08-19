package com.noob.algorithm.daily.plan03.hot100_daily.day10.p030;

/**
 * 🟡 053 最大子数组和 - https://leetcode.cn/problems/maximum-subarray/
 */
public class Solution053_01 {

    /**
     * 思路分析：
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i] 表示以当前i位置元素结尾的具有最大和的连续子数组
        int[] dp = new int[n];

        /**
         * dp 递推：
         * 连续子数组：要么衔接在上一个位置之后，要么另成一派
         * 1.可衔接在上一个元素之后（正效益）
         * 2.另成一派
         */

        //  dp[0] = Math.max(nums[0], 0);
        dp[0] = nums[0];

        // dp 构建
        // int maxVal = Integer.MIN_VALUE;
        int maxVal = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxVal = Math.max(maxVal, dp[i]);
        }

        // 返回结果
        return maxVal;
    }
}
