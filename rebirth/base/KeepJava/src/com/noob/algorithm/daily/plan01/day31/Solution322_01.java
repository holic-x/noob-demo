package com.noob.algorithm.daily.plan01.day31;

import com.noob.algorithm.dmsxl.graph.Pair;

/**
 * 🟡 322 零钱兑换 - https://leetcode.cn/problems/coin-change/description/
 */
public class Solution322_01 {

    public int coinChange(int[] coins, int amount) {

        // 1.dp 定义：dp[j]表示凑成金额j的最最少硬币个数
        int[] dp = new int[amount + 1];

        /**
         * 2.dp 递推
         * dp[j] = min{dp[j],dp[j-coins[i]] + 1}
         */

        // 3.dp 初始化
        dp[0] = 0; // 凑成金额0的硬币最少硬币个数为0
        for (int j = 1; j <= amount; j++) {
            dp[j] = amount + 1; // 初始化设定为一个不可能出现的最大值（因为递推过程要求最小值）
        }

        // 4.dp 构建
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 返回结果
        return dp[amount] > amount ? -1 : dp[amount]; // 如果为初始值说明没有满足的方案
    }
}
