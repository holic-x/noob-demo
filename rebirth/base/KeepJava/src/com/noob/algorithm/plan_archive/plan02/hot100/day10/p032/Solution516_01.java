package com.noob.algorithm.plan_archive.plan02.hot100.day10.p032;

/**
 * 🟡 516 最长回文子序列 - https://leetcode.cn/problems/longest-palindromic-subsequence/
 */
public class Solution516_01 {

    /**
     * 思路分析：动态规划
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // 1.dp 定义（dp[i][j] 表示[i,j]区间范围内的字符串的最长回文子序列长度）
        int[][] dp = new int[n][n];

        /**
         * 2.dp 递推：根据s[i]\s[j]元素是否相同分情况讨论
         * s[i]==s[j]：元素相同，可以理解为可以在[i+1,j-1]的区间基础上加入这2个元素构成更长的回文子序列：dp[i][j] = dp[i+1][j-1]+2
         * s[i]!=s[j]: 元素不相同，说明无法同时加入这两个元素（如果直接加入无法构成回文），只能选择加入1个看是否可达到目的
         *  - 加入s[i]=> dp[i][j] = dp[i][j-1]
         *  - 加入s[j]=> dp[i][j] = dp[i+1][j]
         */

        // 3.dp 初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;//i==j时元素自身为一个回文子序列
        }

        // 4. 遍历顺序
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        // 返回结果(遍历顺序是从下往上，从左往右，因此第一行的最后一个元素为结果)
        return dp[0][n - 1];
    }

}
