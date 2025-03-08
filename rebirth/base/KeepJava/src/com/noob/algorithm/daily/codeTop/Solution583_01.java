package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 583 两个字符串的删除操作 - https://leetcode.cn/problems/delete-operation-for-two-strings/description/
 */
public class Solution583_01 {

    /**
     * 动态规划思路（基于删除元素的角度）
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        // 1.dp 定义：dp[i][j] 表示使得[0,i-1]的word1 和 [0,j-1]的word2 相同所需的最小步数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：
         * dp[i][j] 根据w1[i-1] w2[j-1]是否相同进行分类讨论
         * ① w1[i-1]==w2[j-1] 字符相同，无需操作，继承上一状态 dp[i][j] = dp[i-1][j-1]
         * ② w1[i-1]!=w2[j-1] 字符不同，需要执行删除操作：
         * - dp[i][j] = min{dp[i-1][j]+ 1,dp[i][j-1]+ 1, dp[i-1][j-1] + 2}  选择w1、w2执行删除一个字符操作或者同时删除w1、w2
         */

        // 3.dp 初始化
        dp[0][0] = 0; // 两个字符串都为空字符串，不需要执行操作
        // dp[0][j] 要达到和空字符串w1相同，则需删除自身长度字符
        for (int j = 1; j < n; j++) {
            dp[0][j] = j;
        }
        // dp[i][0] 同理，要达到和空字符串w2相同，需删除自身长度字符
        for (int i = 1; i < m; i++) {
            dp[i][0] = i;
        }

        // 4.dp 构建（确定遍历顺序：从上到下、从左到右）
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
