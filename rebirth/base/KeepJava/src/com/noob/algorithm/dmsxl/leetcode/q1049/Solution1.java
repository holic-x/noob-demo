package com.noob.algorithm.dmsxl.leetcode.q1049;

/**
 * 1049 最后一块石头的重量II
 */
public class Solution1 {

    // 动态规划：二维数组思路
    public int lastStoneWeightII(int[] stones) {
        int m = stones.length;

        // 获取重量之和
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += stones[i];
        }
        int bagSize = sum / 2;

        // 1.dp[]定义（dp[i][j] 表示容量为j的背包放入物品i时的最大价值）
        int[][] dp = new int[m][bagSize + 1];

        // 2.递推公式：dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-stones[i]]+stones[i])

        // 3.dp[]初始化（首行初始化）
        for (int j = 0; j <= bagSize; j++) {
            dp[0][j] = (j >= stones[0]) ? stones[0] : 0;
        }

        // 4.dp[]构建
        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if (j < stones[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                }
            }
        }

        // 返回结果
        return sum - 2 * dp[m - 1][bagSize];
    }

}
