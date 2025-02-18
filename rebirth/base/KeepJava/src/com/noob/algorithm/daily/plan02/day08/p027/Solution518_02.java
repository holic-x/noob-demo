package com.noob.algorithm.daily.plan02.day08.p027;

/**
 * 🟡 518 零钱兑换 - https://leetcode.cn/problems/coin-change-ii/
 */
public class Solution518_02 {

    /**
     * 思路分析：给定coins（表示不同面额的硬币），amount 表示总金额，求可以凑成总结的硬币组合数（不限定硬币个数）
     * 二维数组思路
     */
    public int change(int amount, int[] coins) {
        int m = coins.length, n = amount + 1;
        // 1.dp 定义：dp[i][j] 表示[0,i]的物品i可以凑成金额j的组合个数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：
         * - j >= coins[i] : dp[i][j] = dp[i-1][j] + dp[i-1][j-coins[i]] (选择放或者不放入的情况)
         * - j < coins[i] : dp[i][j] = dp[i-1][j]
         * 需转化为一维数组的形式：
         * - j >= coins[i] : dp[j] = dp[j] + dp[j-coins[i]] + 1;
         * - j < coins[i] : dp[j] = dp[j]
         */

        // 3.dp 初始化
        // 首行初始化：dp[0][j] 用物品0凑成金额j的方案
        for (int j = 0; j < n; j++) {
            dp[0][j] = (j % coins[0] == 0 ? 1 : 0);
        }

        // 首列初始化：dp[i][0] 用[0,i]的物品凑成金额0的方案（由于硬币价值不可能为0，因此根本凑不出,当作只有一种方案就是不凑）
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 凑成金额0的方案只有一种，就是不放入任何硬币
        }

        // 4.dp 构建（完全背包问题：先物品后背包，背包正序）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[m - 1][amount];
    }

}
