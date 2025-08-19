package com.noob.algorithm.daily.plan03.hot100_daily.day10.p030;

/**
 * 🟡 718 最长重复子数组（连续）- https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 */
public class Solution718_01 {
    /**
     * 思路分析：
     */
    public int findLength(int[] nums1, int[] nums2) {
        // dp[i][j] 表示[0,i]范围内的nums1 和 [0,j]范围内的nums2 的最长重复子数组

        /**
         * dp 递推：对于当前校验节点来说，其取决于nums1[i-1]位置和nums[j-1]位置（即左上角位置）元素是否出现连续相等
         * 如果连续相等则认为可衔接（最长重复子数组（连续））
         */
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];

        // 初始化首行首列（对于首行、首列可以理解为是一个便于数组越界边界讨论的设计点优化，此处首行、首列并无前置推导条件，因此初始化均为0）
        for (int j = 0; j <= n; j++) {
            // 首行初始化
            dp[0][j] = 0;
        }
        for (int i = 0; i <= m; i++) {
            // 首列初始化
            dp[i][0] = 0;
        }

        // dp 构建
        int maxVal = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                maxVal = Math.max(maxVal, dp[i][j]);
            }
        }

        return maxVal;
    }
}
