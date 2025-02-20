package com.noob.algorithm.plan_archive.plan02.hot100.day08.p027;

/**
 * 🟡 322 零钱兑换 - https://leetcode.cn/problems/coin-change/description/
 */
public class Solution322_01 {

    /**
     * 返回可以凑成amount的最少硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        int m = coins.length, n = amount + 1;
        // 1.dp 定义：dp[j] 表示[0,i]中的硬币，可以凑满amount的最少硬币个数
        int[] dp = new int[n];
        /**
         * dp 递推
         * - 如果j可以放下硬币i，则可选择放或者不放，则dp[j] = min{dp[j],dp[j-coins[i]]+1}
         *      - 不放：继承上一状态
         *      - 放：先空出容量，随后加入1个硬币
         */
        // 3.dp 初始化（dp[0]）
        dp[0] = 0; // 凑成金额0的方案的最少硬币个数为0
        for (int j = 1; j <= amount; j++) {
            dp[j] = amount + 1; // 初始化设定一个不可能出现的较大值
        }

        // 4.dp 构建（遍历顺序：先物品后背包，正序）
        /*
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
         */
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }


        // 返回结果
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
