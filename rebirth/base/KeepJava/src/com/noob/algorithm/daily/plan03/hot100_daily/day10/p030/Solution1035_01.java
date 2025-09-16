package com.noob.algorithm.daily.plan03.hot100_daily.day10.p030;

/**
 * 🟡 1035 不相交的线 - https://leetcode.cn/problems/uncrossed-lines/description/
 * - 转化为 最长公共子序列问题
 */
public class Solution1035_01 {

    /**
     * 思路分析：
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length + 1, n = nums2.length + 1;

        // 初始化默认为0

        int[][] dp = new int[m][n];

        // dp 递推
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];
    }
}
