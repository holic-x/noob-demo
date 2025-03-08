package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 1143 最长公共子序列 - https://leetcode.cn/problems/longest-common-subsequence/description/
 */
public class Solution1143_01 {
    /**
     * 动态规划思路
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length() + 1, n = text2.length() + 1;
        // 1.dp 定义：dp[i][j] 表示[0,i-1]的字符串text1 与 [0,j-1]的字符串text2 的最长公共子序列
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：根据t1[i-1]与t2[j-1]是否相等来处理
         * t1[i-1]==t2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
         * t1[i-1]!=t2[j-1]: dp[i][j] = max{dp[i-1][j],dp[i][j-1]}
         */

        // 3.dp 初始化
        // dp[0][j]:
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0; // 任意字符串与空字符串的最长公共子序列为空
        }

        // dp[i][j]:
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0; // 任意字符串与空字符串的最长公共子序列为空
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
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
