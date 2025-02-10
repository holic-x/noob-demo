package com.noob.algorithm.daily.archive.plan01.day29;

/**
 * 🟡 062 不同路径 - https://leetcode.cn/problems/unique-paths/description/
 */
public class Solution062_01 {

    /**
     * 动态规划思路：移动到每个位置和其前置位置相关
     */
    public int uniquePaths(int m, int n) {
        // 1.dp 定义（dp[i][j]表示移动到(i,j)位置共有多少条路径）
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推
         * 对于(i,j)其可以由两个方向推导出来（分别是左侧和上侧），因此到达该点的路径总和为dp[i][j] = dp[i][j-1] + dp[i-1][j]
         */

        // 3.dp 初始化(初始化首行、首列)
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1; // 对于首行的每个点始终只有1条路径
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 对于首列的每个点始终只有1条路径
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        // 返回到达右下角共有多少条不同的路径
        return dp[m - 1][n - 1];
    }
}
