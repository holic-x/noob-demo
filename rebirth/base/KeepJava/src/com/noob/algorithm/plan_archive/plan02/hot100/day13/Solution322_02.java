package com.noob.algorithm.plan_archive.plan02.hot100.day13;


/**
 * 🟡 322 零钱兑换 - https://leetcode.cn/problems/coin-change/description/
 */
public class Solution322_02 {

    /**
     * 思路：动态规划思路
     */
    public int coinChange(int[] coins, int amount) {
        int INF = amount + 1; // 设置一个最大值（不可能达到的最大值）
        int m = coins.length, n = amount + 1; // m 物品个数，n背包容量（取到amount）
        // 1.dp 定义：dp[j] 表示用可以凑满总金额j的最少的硬币个数
        int[] dp = new int[n];

        /**
         * 2.dp 递推，dp[j] 对于每个硬币可以选择放入或者不放入
         * dp[j] = min{dp[j],dp[j-coins[i]] +1}
         */

        // 3.dp 初始化
        dp[0] = 0; // 容量为0，无法放入任何硬币
        for (int i = 1; i < n; i++) {
            dp[i] = INF;
        }

        // 4.dp 构建
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 返回结果
        return dp[amount] >= INF ? -1 : dp[amount];
    }
}
