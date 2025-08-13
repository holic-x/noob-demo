package com.noob.algorithm.daily.plan03.hot100_daily.day08.p025;

/**
 * 🟡 062 不同路径 - https://leetcode.cn/problems/unique-paths/submissions/600329706/
 * 机器人只能向下或者向右移动1步骤，求机器人试图到达右下角（m、n）的位置可有多少条不同的路径
 */
public class Solution062_01 {

    /**
     * 思路分析：
     */
    public int uniquePaths(int m, int n) {
        // dp[i][j] 表示到达位置(i,j)可有x条不同的路径
        int[][] dp = new int[m + 1][n + 1];

        // 行、列：起点位置的同一行走、同一列走都视作只有一条
        dp[0][0] = 1;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }

        // 遍历剩余位置元素
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 上面的位置下来1步 + 左侧的位置右移1步
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回结果
        return dp[m][n];
    }
}