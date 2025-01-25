package com.noob.algorithm.daily.plan01.archive.day31;

/**
 * 🟡 518 零钱兑换 - https://leetcode.cn/problems/coin-change-ii/
 */
public class Solution518_01 {

    /**
     * 动态规划：完全背包问题
     */
    public int change(int amount, int[] coins) {
        int m = coins.length, n = amount + 1;
        // 1.dp 定义：dp[i][j] 表示使用[0,i]的硬币构成金额j的硬币组合数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：
         * - j<coins[i]: 继承上一状态，dp[i-1][j]
         * - j≥coins[i]: 选择放入或者不放当前物品, dp[i-1][j] + dp[i][j-coins[i]]
         */

        // 3.dp 初始化
        // 首行初始化（物品0，装入容量j构成的硬币组合数）
        for (int j = 0; j < n; j++) {
            // 金额j对coins[0]求余如果为0得到1种方案
            dp[0][j] = (j % coins[0] == 0 ? 1 : 0);
        }

        // 首列初始化（构成金额为0的方案只有1种，就是不放）
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) { // 先物品
            for (int j = 1; j < n; j++) { // 后背包（根据j与coins[i]的关系控制）
                if (j < coins[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];
    }
}
