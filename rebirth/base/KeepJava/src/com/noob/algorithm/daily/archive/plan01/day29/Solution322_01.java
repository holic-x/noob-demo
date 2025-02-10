package com.noob.algorithm.daily.archive.plan01.day29;

/**
 * 🟡 322.零钱兑换 - https://leetcode.cn/problems/coin-change/description/ todo
 * 无法ac
 */
public class Solution322_01 {

    /**
     * 思路：完全背包问题处理
     */
    public int coinChange(int[] coins, int amount) {
        // m 表示物品个数，n 表示背包容量
        int m = coins.length, n = amount + 1;
        // 1.dp 定义（dp[i][j]表示用[0,i]范围内的硬币凑成金额j的最少个数（装满背包所有物品的最小个数））
        int[][] dp = new int[m][n];

        /**
         * 2.dp 推导
         * j >= coins[i]: dp[i][j] = min{dp[i-1][j],dp[i-1][j-coins[i]] + 1}
         */

        // 3.dp 初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = amount + 1; // 初始化其他元素均为一个不可能的数
                if (i == 0) {
                    // 首行元素初始化：如果背包容量刚好能装满则装配
                    dp[0][j] = (j == coins[0] ? 1 : amount + 1);
                }
                if (j == 0) {
                    // 首列元素初始化：初始容量j=0，coins不为负数的情况下无法装入任何硬币（默认均为-1）
                    dp[i][0] = 0; // 凑满金额0的最少硬币个数为0
                }
            }
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - coins[i]] + 1);
                }
            }
        }

        // 返回结果
        return dp[m - 1][amount] > amount ? -1 : dp[m - 1][amount];
    }


}
