package com.noob.algorithm.plan_archive.plan01.day29;

/**
 * 🟡 322.零钱兑换 - https://leetcode.cn/problems/coin-change/description/ todo
 */
public class Solution322_02 {

    /**
     * 思路：完全背包问题处理(一维数组处理方式)
     */
    public int coinChange(int[] coins, int amount) {
        // 1.dp 定义：dp[j]表示凑成金额j的最少硬币个数
        int[] dp = new int[amount + 1]; // 数组从0开始计数，要取值取到amount，则数组定义到amount+1

        /**
         * 2.dp 递推
         * dp[j] = min{dp[j],dp[j-coins[i] + 1]}
         */

        // 3.dp 初始化
        dp[0] = 0; // 凑满金额0的最少硬币个数为0
        for (int j = 1; j <= amount; j++) {
            dp[j] = amount + 1; // 初始化设定一个不可能出现的较大值
        }
        // Arrays.fill(dp, amount + 1); // 初始化设置为一个不可能出现的值
        // dp[0] = 0;

        // 4.dp 构建
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 返回结果
        return dp[amount] > amount ? -1 : dp[amount];
    }


}
