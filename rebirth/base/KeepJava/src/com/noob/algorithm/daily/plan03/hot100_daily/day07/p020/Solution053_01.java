package com.noob.algorithm.daily.plan03.hot100_daily.day07.p020;

/**
 * 🟡 053 最大子数组和 - https://leetcode.cn/problems/maximum-subarray/description/
 */
public class Solution053_01 {

    /**
     * 思路分析：
     * 连续子数组的设定：不改变原有数组的顺序，定义以每个位置上元素结尾的最大子数组和，这个数值可能有2种情况，要满足以该元素结尾
     * - 直接为当前元素nums[i]，另启一派
     * - 上一个位置的最大子数组和+nums[i] 表示可拼接构成更大的和
     * - 基于上面两种场景选择一个最优策略
     */
    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        /*
        for(int i=0;i<n;i++){
            dp[i] = nums[i];
        }
         */
        dp[0] = nums[0];

        // 记录处理过程中的最大子数组和
        int maxSum = Math.max(Integer.MIN_VALUE, dp[0]);
        for (int i = 1; i < n; i++) {
            /*
            if (nums[i] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
             */
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            // 更新maxSum
            maxSum = Math.max(maxSum, dp[i]);
        }
        // 返回处理结果
        return maxSum;
    }
}
