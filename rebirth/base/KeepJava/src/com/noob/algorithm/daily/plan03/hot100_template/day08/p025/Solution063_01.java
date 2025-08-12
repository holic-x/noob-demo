package com.noob.algorithm.daily.plan03.hot100_template.day08.p025;

/**
 * 🟡 063 不同路径II - https://leetcode.cn/problems/unique-paths-ii/description/
 * 机器人只能往下或者往右，且网格可能存在障碍物（1障碍物、0空位置），判断多少条路径可到达右下角
 */
public class Solution063_01 {

    /**
     * 思路分析：
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // dp[i][j] 表示到达(i,j)位置的路径数量
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // dp初始化
        dp[0][0] = 1;

        // 首行：一旦遇到障碍物则后面的位置均不可达
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break; // 遇到障碍跳出
            }
            dp[0][j] = 1;
        }

        // 首列：一旦遇到障碍物则后面的位置均不可达
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break; // 遇到障碍跳出
            }
            dp[i][0] = 1;
        }

        // 处理其他位置元素
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 判断当前位置是否存在障碍
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] =0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];

    }
}
