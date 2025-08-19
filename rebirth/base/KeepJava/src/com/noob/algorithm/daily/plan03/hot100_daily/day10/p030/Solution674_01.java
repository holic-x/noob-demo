package com.noob.algorithm.daily.plan03.hot100_daily.day10.p030;

import java.util.Arrays;

/**
 * 🟢 674 最长连续递增序列 - https://leetcode.cn/problems/longest-continuous-increasing-subsequence/description/
 */
public class Solution674_01 {

    /**
     * 思路分析：
     * 最长连续递增子序列
     */
    public int findLengthOfLCIS(int[] nums) {

        // dp[i] 表示以i位置元素结尾的最长连续递增子序列长度：涉及连续要么衔接在上一个元素后面，要么另成一派
        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        // dp 构建
        int maxVal = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1; // 自成一派
            }
            maxVal = Math.max(maxVal, dp[i]);
        }
        return maxVal;
    }
}
