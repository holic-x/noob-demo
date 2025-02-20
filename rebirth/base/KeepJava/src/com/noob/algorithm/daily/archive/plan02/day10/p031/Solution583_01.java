package com.noob.algorithm.daily.archive.plan02.day10.p031;

/**
 * 🟡 583 两个字符串的删除操作 - https://leetcode.cn/problems/delete-operation-for-two-strings/description/
 */
public class Solution583_01 {

    /**
     * 思路分析：求要使得word1、word2相同所需的最小步数（仅限删除操作：每步可删除任意一个字符串的一个字符）
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        // 1.dp 定义：dp[i][j] 表示要使得以[0,i-1]的word1\[0,j-1]的word2达到相同时所需的最小步数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：基于当前遍历位置元素是否相同进行分情况讨论
         * w1[i-1]==w2[j-1] 说明元素相同，不需要执行操作=>dp[i][j]=dp[i-1][j-1]
         * w1[i-1]!=w2[j-1] 说明元素不相同，需要考虑删除操作，有下述几种情况讨论
         * - 删除w1中的1个字符达到相同
         * - 删除w2中的1个字符达到相同
         * - 同时删除w1、w2中的2个字符达到相同
         * => dp[i][j] = min{dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+2}
         */

        // 3.dp 初始化
        // 首行初始化（w1为空字符串，要让w2与w1相同则需要删掉自身长度的字符）
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }
        // 首列初始化（同理，此处w2为空字符串，要让w1与w2相同则需要删掉自身长度的字符）
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];

    }
}
