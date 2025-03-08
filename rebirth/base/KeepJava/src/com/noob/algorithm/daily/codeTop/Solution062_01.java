package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 062 不同路径 - https://leetcode.cn/problems/unique-paths/
 */
public class Solution062_01 {

    /**
     * 思路：动态规划
     * 机器人要走到[m,n]点，则需从其上方坐标或者左侧坐标进行推演
     * dp[i][j] = dp[i-1][j] + dp[i][j-1] // 方案推演
     */
    public int uniquePaths(int m, int n) {
        // 1.dp 定义：dp[i][j] 表示从[0,0]位置走到点[i,j]的路径数
        int[][] dp = new int[m][n]; // 如果坐标要取到[m,n]，则此处初始化为[m+1][n+1]

        /**
         * 2.dp 递推：
         * dp[i][j] = dp[i-1][j] + dp[i][j-1]
         */

        // 3.dp 初始化
        // dp[0][j] 对于首行，路径始终只有一条
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // dp[i][0] 对于首列，路径始终只有一条
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回
        return dp[m - 1][n - 1];
    }
}
