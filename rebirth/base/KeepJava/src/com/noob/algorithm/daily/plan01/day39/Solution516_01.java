package com.noob.algorithm.daily.plan01.day39;

/**
 * 🟡 516 最长回文子序列 - https://leetcode.cn/problems/longest-palindromic-subsequence/description/
 */
public class Solution516_01 {

    /**
     * 思路分析：最长回文子序列，基于动态规划思路
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // 1.dp 定义：dp[i][j] 表示[i,j]区间范围内的最长回文子序列长度
        int[][] dp = new int[n][n];

        /**
         * 2.dp 递推：根据s[i]==s[j]是否成立进行校验
         * ① s[i]==s[j]: dp[i][j] = dp[i+1][j-1] + 2
         * ② s[i]!=s[j]: dp[i][j] = max{dp[i+1][j],dp[i][j-1]}
         */

        // 3.dp 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = (i == j) ? 1 : 0; // 元素自身为一个回文子序列
            }
        }

        // 4.dp 构建
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回结果
        return dp[0][n - 1];
    }

}
