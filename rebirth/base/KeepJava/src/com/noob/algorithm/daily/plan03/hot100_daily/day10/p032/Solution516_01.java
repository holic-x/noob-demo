package com.noob.algorithm.daily.plan03.hot100_daily.day10.p032;

/**
 * 🟡 516 最长回文子序列 - https://leetcode.cn/problems/longest-palindromic-subsequence/
 */
public class Solution516_01 {

    /**
     * 思路分析：两边缩圈的概念  ❌推导入错误，应该是`i.i+1............j-1.j`的场景推导（从外往内，而不是从内向外扩散）
     * s[i] == s[j] : 可以将这两个元素加入回文序列（dp[i][j] = dp[i+1][j-1]+2）
     * s[i] != s[j] : 两个元素不能同时加入回文序列，只能加入A或B （dp[i][j] = max{dp[i+1][j],dp[i][j-1]}）
     */
    public int longestPalindromeSubseq(String s) {
        // dp[i][j] 表示 [i,j]范围内的最长回文序列
        int n = s.length();

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;//i==j时元素自身为一个回文子序列
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

}
