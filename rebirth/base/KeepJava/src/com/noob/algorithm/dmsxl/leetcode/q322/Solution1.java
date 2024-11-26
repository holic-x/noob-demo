package com.noob.algorithm.dmsxl.leetcode.q322;

/**
 * 322 零钱兑换
 */
public class Solution1 {

    /**
     * 完全背包问题转化
     *
     * @param coins  物品数
     * @param amount 背包容量
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        // 1.dp 定义：dp[j]表示凑满j金额所需的最少硬币个数
        int[] dp = new int[amount + 1];

        /**
         * 2.dp 推导
         * dp[j] = min{dp[j], dp[j-coins[i]]+1 }
         */

        // 3.dp 初始化
        dp[0] = 0; // 凑满金额0的最少硬币个数为0
        for (int j = 1; j <= amount; j++) {
            dp[j] = amount + 1; // 初始化设定一个不可能出现的较大值
        }

        // 4.构建dp
        // 先物品后背包容量 + 背包容量正序方案
        /*
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
         */
        // 先背包容量后物品 + 背包容量正序方案
        for (int j = 0; j <= amount; j++) {
            for (int i = 0; i < coins.length; i++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 返回结果(对初始值过滤)
        return dp[amount] > amount ? -1 : dp[amount]; // 如果为初始值说明没有满足的方案
    }

}
