package com.noob.algorithm.daily.plan01.day42;

/**
 * 🟡 072 编辑距离 - https://leetcode.cn/problems/edit-distance/description/
 */
public class Solution072_01 {

    /**
     * 动态规划思路
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // 1.dp 定义：dp[i][j] 表示以i-1位置结尾的word1 转化为以j-1位置结尾的word2所使用的最少操作数
        int[][] dp = new int[len1 + 1][len2 + 1];

        /**
         * 2.dp 推导：思考如何将word1转化为word2，需要比较w1[i-1]与w2[j-1]是否匹配
         * ① w1[i-1]==w2[j-1] 不需要执行操作 =》dp[i][j] = dp[i-1][j-1]
         * ② w1[i-1]!=w2[j-1]，需要思考执行删除、新增、替换操作来达到目的
         * - 2.1 w1 删除一个字符 或 w2 删除一个字符：dp[i][j] = min{dp[i-1][j]+1,dp[i][j-1]+1}
         * - 2.2 新增和删除概念类似 效果相同
         * - 2.3 替换w1或w2替换1个字符（在原基础上执行1次）：dp[i][j] = dp[i-1][j-1] + 1
         */

        // 3.dp 初始化
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j; // word2需要执行自身长度次数的删除操作得到空字符串word1
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i; // word1需要执行自身长度次数的删除操作得到空字符串word2
        }

        // 4.dp 构建
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        // 返回结果
        return dp[len1][len2];
    }
}
