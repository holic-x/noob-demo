package com.noob.algorithm.daily.archive.plan02.hot100.day10.p030;

/**
 * 🟡 1143 最长公共子序列（不连续） - https://leetcode.cn/problems/longest-common-subsequence/description/
 */
public class Solution1143_01 {

    /**
     * 思路分析：动态规划
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length() + 1, n = text2.length() + 1;
        // 1.dp 定义（dp[i] 表示[0,i-1]的text1、[0,j-1]的text2 的最长公共子序列长度）
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推（根据当前遍历字符是否相等来进行校验）
         * - t1[i-1]==t2[j-1]: 可拼接构成更长的公共子序列 => dp[i][j] = dp[i-1][j-1] + 1
         * - t1[i-1]!=t2[j-1]: 从其左侧、上侧选一个较大的值继承过来 => dp[i][j] = max{dp[i][j-1],dp[i-1][j]}
         */

        // 3.dp 初始化（首行首列初始化）
        // 首行初始化
        for (int j = 0; j < n; j++) { // text1为空，则最长公共子序列长度只能为空
            dp[0][j] = 0;
        }
        // 首列初始化
        for (int i = 0; i < m; i++) { // text2为空，则最长公共子序列长度只能为空
            dp[i][0] = 0;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];
    }
}
