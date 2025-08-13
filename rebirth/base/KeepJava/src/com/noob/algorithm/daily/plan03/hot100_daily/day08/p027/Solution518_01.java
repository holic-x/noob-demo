package com.noob.algorithm.daily.plan03.hot100_daily.day08.p027;

/**
 * 🟡 518 零钱兑换II - https://leetcode.cn/problems/coin-change-ii/
 */
public class Solution518_01 {

    /**
     * 思路分析：凑硬币的组合数（凑满目标金额）
     * 完全背包、组合（先背包后物品）
     */
    public int change(int amount, int[] coins) {
        // 1.dp定义:dp[i] 表示从coins中选择可以凑成总硬币的组合数
        int m = coins.length, n = amount + 1;
        int[] dp = new int[n];

        dp[0] = 1; // 金额为0，无组合

        // 构建dp
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 判断是否可以放入该硬币，如果可以放入才选择放不放
                if (j >= coins[i]) {
                    // 组合数概念：放+不放
                    dp[j] += dp[j - coins[i]];
                }
            }
        }

        // 返回结果
        return dp[amount];
    }

}
