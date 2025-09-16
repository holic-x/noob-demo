package com.noob.algorithm.daily.plan03.hot100_daily.day10.p031;

/**
 * 🟡 072 编辑距离 - https://leetcode.cn/problems/edit-distance/description/
 */
public class Solution072_01 {
    /**
     * 思路分析：
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数
     * - 可对一个单词插入、删除、替换字符
     */
    public int minDistance(String word1, String word2) {
        // dp[i][j] 将[0,i-1]范围的word1 转化为 [0,j-1]范围的word2 所需的最少操作数
        int m = word1.length() + 1, n = word2.length() + 1;


        // dp 初始化
        int[][] dp = new int[m][n];
        // dp[0][j] 首行初始化，word1为空字符串，要转化为word2则需按需插入字符
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }
        // dp[i][0] 首列初始化，word2为空字符串，要将word1转化为word2则需按需删除字符
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                /**
                 * 判断word1[i-1] 和 word2[j-1]
                 * - 如果相同，则不需要做操作(状态继承)
                 * - 如果不相同，则需考虑插入、删除、替换的成本
                 */
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 增加/删除 A+1=B-1  A-1=B+1  或者 替换（在dp[i-1][j-1]基础上进行替换）
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}