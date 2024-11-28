package com.noob.algorithm.dmsxl.leetcode.q300;

/**
 * 300 最长递增子序列
 */
public class Solution1 {
    // 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 1.dp定义(dp[i]表示以`i`位置元素结尾的最大连续递增子序列长度)
        int[] dp = new int[n];

        /**
         * 2.dp递推
         * 以nums[i]结尾只有两种可能
         * 1.要么为元素自身构成一个序列：dp[i] = 1
         * 2.要么是可以拼接在[0,i]内元素后面，并且构成最大连续递增子序列：dp[i] = max{dp[j] + 1}  (j∈{0，i])
         */

        // 3.dp初始化
        dp[0] = 1; // 元素自身为一个递增子序列

        // 4.dp构建
        for (int i = 1; i < n; i++) { // 外层确定i
            int curMax = 1; // 初始化为【元素为自身的情况】
            for (int j = 0; j < i; j++) { // 内层从[0,i]中择选可以构成最大连续递增子序列的元素，选择最长的那个
                // 只有nums[i]>nums[j]才能构建连续递增
                if (nums[i] > nums[j]) {
                    // 选择最大的长度
                    curMax = Math.max(curMax, dp[j] + 1);
                }
            }
            // 更新dp[i]
            dp[i] = curMax;
        }

        // 结果处理
        int maxLen = 0;
        for (int i = 0; i < dp.length; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
