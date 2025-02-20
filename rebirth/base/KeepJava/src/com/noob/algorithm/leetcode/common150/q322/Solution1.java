package com.noob.algorithm.leetcode.common150.q322;

import java.util.Arrays;

/**
 * 322 零钱兑换
 */
public class Solution1 {
    /**
     * 动态规划：背包问题（外层背包，内层物品）
     * 用最少的硬币个数凑零钱
     */
    public int coinChange(int[] coins, int amount) {
        // 1.dp 定义：dp[i] 表示凑够i零钱需要的最少硬币数
        int[] dp = new int[amount + 1];

        // 2.初始化dp(求min,此处除了dp[0]，其他元素可以设为MAX,或者一个无业务影响的数值（例如amount+1）)
        Arrays.fill(dp, amount + 1); // Integer.MAX_VALUE
        dp[0] = 0;
        /**
         * 3.状态转移方程
         * dp[i] 如何获得，其最理想的状态就是减去一个任意硬币金额，然后数量+1 即可得到最优解，但这个硬币的方案选择有很多种，因此要择选min
         * 即 dp[i] = min{ dp[i] , dp[i-coins[j]] + 1}
         * 只要保证dp[i-count[j]]也是最少硬币数即可获得最优解
         */
        // 4.构建dp 寻找最优解
        for (int i = 1; i <= amount; i++) { // 外层循环确定i位置，用于构建dp[i]
            for (int j = 0; j < coins.length; j++) { // 内层循环寻找最优解（找到最理想的dp[i]）
                // 此处需限定条件coins[j]<i 否则数组越界异常
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 返回结果
        return dp[amount] > amount ? -1 : dp[amount]; // 区分是否可以凑的情况
    }
}
