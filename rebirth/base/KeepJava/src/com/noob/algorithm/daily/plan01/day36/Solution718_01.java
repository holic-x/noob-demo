package com.noob.algorithm.daily.plan01.day36;

/**
 * 🟡 718 最长重复子数组 - https://leetcode.cn/problems/maximum-length-of-repeated-subarray/description/
 */
public class Solution718_01 {

    /**
     * 动态规划：将数组元素展成二维数组进行比较处理
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length + 1, n = nums2.length + 1;
        // 1.dp 定义：dp[i][j] 表示字符串为[0,i-1]、[0,j-1]的公共子数组的最长长度
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：如果是连续的公共子数组，则其推导状态从其左上推导得出，从左上到右下构成的连续1的最长长度
         */

        // 3.dp 初始化（首行、首列初始化）
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
        int maxLen = -1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 如果当前比较位置元素相同，则基于其左上的状态推导
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        // 返回结果
        return maxLen;
    }
}
