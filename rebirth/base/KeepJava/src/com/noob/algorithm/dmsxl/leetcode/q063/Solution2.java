package com.noob.algorithm.dmsxl.leetcode.q063;

/**
 * 063 不同路径II
 */
public class Solution2 {

    /**
     * 动态规划
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        // 特例判断：如果是起点或者终点遇到障碍则返回0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // 1.定义dp[i][j]:表示从(0,0)到(i,j)位置共有多少条路径
        int[][] dp = new int[m][n]; // 如果m、n要作为数组下标的话，此处长度要扩宽为[m+1][n+1]
        /**
         * 2.递推公式
         * dp[i,j] 只能从两个方向过来：(i-1,j)向下走1步；(i,j-1)向右走1步
         * 基于此构建递推公式为：dp[i][j]=dp[i-1][j] + dp[i][j-1]
         */
        // 3.dp 初始化((0,0)往同一行或者同一列方向行进，路径始终只有1条)
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1; // 指定位置无障碍才有可通行路径
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1; // 指定位置无障碍才有可通行路径
        }
        // 4.构建dp（从(1,1)节点开始）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 判断障碍物：如果(i,j)位置存在障碍物说明此路不通，则(0,0)到(i,j)的路径应为0
                dp[i][j] = (obstacleGrid[i][j] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        // 返回结果（(0,0)->(m-1,n-1)共有多少条路径）
        return dp[m - 1][n - 1];
    }

}
