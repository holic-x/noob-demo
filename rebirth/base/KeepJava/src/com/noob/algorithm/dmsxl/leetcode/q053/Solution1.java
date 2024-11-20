
package com.noob.algorithm.dmsxl.leetcode.q053;

/**
 * 最大子序和：最大连续子数组和
 */
class Solution1 {
    // 动态规划思路
    public int maxSubArray(int[] nums) {
        // 1.定义dp（dp[i] 表示以当前元素结尾的最大连续子数组和）
        int[] dp = new int[nums.length];

        // 2.初始化dp
        dp[0] = nums[0];

        // 3.状态转移方程:dp[i]=max{dp[i-1]+nums[i],nums[i]}

        // 4.构建dp
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        // 再次遍历构建好的dp数组，获取到max
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            maxVal = Math.max(maxVal, dp[i]);
        }
        // 返回结果
        return maxVal;
    }
}