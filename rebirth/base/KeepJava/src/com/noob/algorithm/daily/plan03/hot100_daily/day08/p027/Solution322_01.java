package com.noob.algorithm.daily.plan03.hot100_daily.day08.p027;

import java.util.Arrays;

/**
 * 🟡 322 零钱兑换 - https://leetcode.cn/problems/coin-change/description/
 */
public class Solution322_01 {

    /**
     * 思路分析：完全背包、组合问题 =》先背包后物品 正序排序
     */
    public int coinChange(int[] coins, int amount) {
        int INF = amount + 1; // 设置一个最大值（不可能达到的最大值）
        // dp[i] 表示用coins中的硬币可以组成（凑满金额i）的最少硬币个数
        int m = coins.length, n = amount + 1;
        int[] dp = new int[n];

        // 初始化
        Arrays.fill(dp, INF); // 初始化为max
        dp[0] = 0; // 容量为0无法放入任何硬币

        for (int j = 0; j <= amount; j++) { // 背包从1开始
            for (int i = 0; i < m; i++) {
                if (j >= coins[i]) { // 条件限制
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }


        // 返回结果
        return dp[amount] > amount ? 0 : dp[amount];
    }
}
