package com.noob.algorithm.daily.archive.plan02.hot100.day10.p030;

/**
 * 🟡 1035 不相交的线 - https://leetcode.cn/problems/uncrossed-lines/description/
 */
public class Solution1035_01 {

    /**
     * 思路分析：转化为求两个子数组的最长公共子序列
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length + 1, n = nums2.length + 1;
        // 1.dp定义（dp[i][j] 表示以[0,i-1]范围的nums1、[0,j-1]范围的nums2 的最长公共子序列长度）
        int[][] dp = new int[m][n];

        /**
         * 2.dp 构建：根据遍历位置元素是否相等分情况讨论
         * nums1[i-1]==nums2[j-1] => dp[i][j] = dp[i-1][j-1] + 1
         * nums1[i-1]!=nums2[j-1] => dp[i][j] = max{dp[i][j-1],dp[i-1][j]} 从前面的状态继承
         */

        // 3.dp 初始化
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
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
