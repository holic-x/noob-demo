package com.noob.algorithm.daily.archive.plan02.day08.p027;

/**
 * 🟡 518 零钱兑换 - https://leetcode.cn/problems/coin-change-ii/
 */
public class Solution518_01 {

    /**
     * 思路分析：给定coins（表示不同面额的硬币），amount 表示总金额，求可以凑成总结的硬币组合数（不限定硬币个数）
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // 1.dp 定义：dp[j] 表示[0,i]的物品i可以凑成金额j的组合个数
        int[] dp = new int[amount + 1];

        /**
         * 2.dp 递推：
         * - j >= coins[i] : dp[i][j] = dp[i-1][j] + dp[i-1][j-coins[i]] (选择放或者不放入的情况)
         * - j < coins[i] : dp[i][j] = dp[i-1][j]
         * 需转化为一维数组的形式：
         * - j >= coins[i] : dp[j] = dp[j] + dp[j-coins[i]] + 1;
         * - j < coins[i] : dp[j] = dp[j]
         */

        // 3.dp 初始化
        dp[0] = 1; // 金额为0，只有1种方案（就是啥也不放）

        // 4.dp 构建（完全背包问题：先物品后背包，背包正序）
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                } else {
                    dp[j] = dp[j]; // 省略
                }
            }
        }

        // 返回结果
        return dp[amount];
    }

}
