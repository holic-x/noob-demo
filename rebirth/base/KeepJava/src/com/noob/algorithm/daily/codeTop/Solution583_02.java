package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 583 两个字符串的删除操作 - https://leetcode.cn/problems/delete-operation-for-two-strings/description/
 */
public class Solution583_02 {

    /**
     * 动态规划思路（基于最长公共子序列的角度）
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        // 1.dp 定义：dp[i][j] 表示 [0,i-1]的word1 和 [0,j-1]的word2 的最长公共子序列长度
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：
         * dp[i][j] 根据w1[i-1] w2[j-1]是否相同进行分类讨论
         * ① w1[i-1]==w2[j-1]: 当前比较字符相同，序列+1 => dp[i][j] = dp[i-1][j] + 1
         * ② w1[i-1]!=w2[j-1]: 当前比较字符不同，继承状态 => dp[i][j] = max{dp[i-1][j],dp[i][j-1]}
         */

        // 3.dp 初始化
        dp[0][0] = 0; // 两个字符串都为空字符串，最长公共子序列长度为0
        // dp[0][j] 其中一个为空，则最长公共子序列为0
        for (int j = 1; j < n; j++) {
            dp[0][j] = 0;
        }
        // dp[i][0]
        for (int i = 1; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建（确定遍历顺序：从上到下、从左到右）
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
