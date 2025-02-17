package com.noob.algorithm.daily.plan02.day08.p025;

/**
 * 🟡 063 不同路径II - https://leetcode.cn/problems/unique-paths-ii/description/
 */
public class Solution063_01 {

    /**
     * 思路分析：机器人只能往下或者往右，且网格可能存在障碍物（1障碍物、0空位置），判断多少条路径可到达右下角
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        // 1.dp 定义 dp[i][j] 表示到达位置（i，j）有多少条路径
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推
         * （i，j）只能从其左侧、上侧移动一步到达，但是如果出现当前这个位置是障碍物，则不可达
         */

        // 3.初始化（一旦出现障碍物，后面的路径都不可达到）
        for(int j=0;j<n;j++){
            if(obstacleGrid[0][j]==1){
                break; // 一旦出现障碍物则跳出处理，后面的路径均不可达
            }
            dp[0][j] = 1;
        }
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==1){
                break; // 一旦出现障碍物则跳出处理，后面的路径均不可达
            }
            dp[i][0] =1;
        }

        // 4.dp 构建
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // 如果当前遍历位置是障碍物，则路径不可达
                if(obstacleGrid[i][j]==1){
                    dp[i][j] = 0;
                }else if(obstacleGrid[i][j]==0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        // 返回结果
        return dp[m-1][n-1];

    }
}
