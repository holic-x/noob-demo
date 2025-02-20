package com.noob.algorithm.daily.archive.plan02.hot100.day08.p025;

/**
 * 🟡 062 不同路径 - https://leetcode.cn/problems/unique-paths/submissions/600329706/
 */
public class Solution062_01 {

    /**
     * 思路分析：机器人只能向下或者向右移动1步骤，求机器人试图到达右下角（m、n）的位置可有多少条不同的路径
     */
    public int uniquePaths(int m, int n) {
        // 1.dp 定义：dp[m][n] = 表示到达第m行、n列的位置共有多少条路径
        int[][] dp = new int[m + 1][n + 1];

        /**
         * 2.dp 递推: 当前位置可以从两个方向过来，上侧、左侧
         * dp[i][j] = dp[i-1][j] + dp[i][j-1]
         */

        // 3.dp 初始化
        // 首行、首列初始化
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1; // 首行始终只有一条直线路径一路往右过来
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;// 首列始终只有一条直线路径一路往下过来
        }

        // 4.dp 构建
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回到达m n位置的路径数量
        return dp[m][n];

    }
}