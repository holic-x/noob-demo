package com.noob.algorithm.daily.plan01.day36;

import java.util.Arrays;

/**
 * 🟡 300 最长递增子序列 - https://leetcode.cn/problems/longest-increasing-subsequence/description/
 */
public class Solution300_01 {
    /**
     * 动态规划思路：
     * dp[i] 表示以i位置结尾的最长子序列长度，遍历到第i个元素时则看其可拼接在[0,i）的哪个位置j之后可构成最大子序列
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 1.dp 定义：dp[i] 表示以i位置结尾的最长子序列长度
        int[] dp = new int[n];

        /**
         * 2.dp 递推：
         * 遍历到第i个元素时则看其可拼接在[0,i）的哪个位置j之后可构成最大子序列
         */

        // 3.dp 初始化
        Arrays.fill(dp, 1); // 元素自身可以视作为一个子序列

        // 4.dp 构建
        int maxLen = dp[0]; // 初始化
        for (int i = 1; i < n; i++) { // 外层遍历i，确定以i位置元素结尾
            for (int j = 0; j < i; j++) { // 内层遍历j，寻找可以拼接在后面形成递增子序列的匹配情况并得到一个最大值
                // 连续递增子序列校验
                if (nums[j] < nums[i]) { // i位置元素可以拼接在当前j位置元素后面，更新最大子序列长度，遍历每一种可能
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }

        // 返回结果
        return maxLen;
    }
}
