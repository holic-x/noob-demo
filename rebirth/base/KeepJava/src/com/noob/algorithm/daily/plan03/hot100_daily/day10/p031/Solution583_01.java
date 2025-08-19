package com.noob.algorithm.daily.plan03.hot100_daily.day10.p031;

/**
 * 🟡 583 两个字符串的删除操作 - https://leetcode.cn/problems/delete-operation-for-two-strings/description/
 */
public class Solution583_01 {

    /**
     * 思路分析：
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数
     * 每个步骤可执行删除操作
     * - 删除word1字符 或 删除word2字符
     */
    public int minDistance(String word1, String word2) {
        // dp[i][j] 表示使得[0,i-1]的word1、[0,j-1]的word2相同所需的最小步数（仅限删除操作）
        int m = word1.length() + 1, n = word2.length() + 1;

        int[][] dp = new int[m][n];

        dp[0][0] = 0;
        for (int j = 1; j < n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = i;
        }

        // 构建dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 相同则无需操作，继承状态
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 不相同，需执行删除操作，要么删掉word1字符、要么删掉word2字符
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];

    }
}
