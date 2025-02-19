package com.noob.algorithm.daily.plan02.day10.p031;

/**
 * 🟡 583 两个字符串的删除操作 - https://leetcode.cn/problems/delete-operation-for-two-strings/description/
 */
public class Solution583_02 {

    /**
     * 思路分析：求要使得word1、word2相同所需的最小步数（仅限删除操作：每步可删除任意一个字符串的一个字符）
     * 思路转化：求最长公共子序列长度（x），那么删除所需的最小步数为len1+len2 - 2*x
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        // 1.dp 定义：dp[i][j] 表示要使得以[0,i-1]的word1\[0,j-1]的word2 的最长公共子序列长度
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：基于当前遍历位置元素是否相同进行分情况讨论
         * w1[i-1]==w2[j-1] 说明元素相同 => dp[i][j] = dp[i-1][j-1]+1
         * w1[i-1]!=w2[j-1] 说明元素不同 => dp[i][j] = max{dp[i-1][j],dp[i][j-1]}
         */

        // 3.dp 初始化
        // 首行初始化
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        // 首列初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回结果
        return word1.length() + word2.length() - 2 * dp[m - 1][n - 1];

    }
}
