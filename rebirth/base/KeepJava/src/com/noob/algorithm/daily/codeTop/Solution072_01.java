package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 072 编辑距离 - https://leetcode.cn/problems/edit-distance/description/
 */
public class Solution072_01 {

    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        // 1.dp 定义：dp[i][j] 表示使得[0,i-1]的word1 与 [0,j-1]的word2 字符串相同所需的最小操作数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：根据w1[i-1]与w2[j-1]的值来校验
         * w1[i-1]==w2[j-1]: 不需要操作，继承上一个状态 dp[i][j] = dp[i-1][j-1]
         * w1[i-1]!=w2[j-1]: 需要执行操作（选择插入、删除、替换）
         * - 选择删除w1或者删除w2的一个字符：dp[i][j] = min{dp[i-1][j] + 1, dp[i][j-1] + 1}
         * - 选择替换：在原有的基础上替换一个元素 dp[i][j] = dp[i-1][j-1] + 1
         */

        // 3.dp 构建
        // dp[0][j] 要让word2变为空字符串则需要删除相应长度的字符
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }
        // dp[i][0] 要让word1变为空字符串则需要删除相应长度的字符
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        // 返回结果
        return dp[m-1][n-1];
    }
}
