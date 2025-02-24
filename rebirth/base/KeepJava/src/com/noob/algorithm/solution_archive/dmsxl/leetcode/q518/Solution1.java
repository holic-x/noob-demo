package com.noob.algorithm.solution_archive.dmsxl.leetcode.q518;

/**
 * 518 零钱兑换
 */
public class Solution1 {
    // 动态规划（一维数组版本）
    public int change(int amount, int[] coins) {
        // 1.dp[j]: 凑成总金额为j的硬币组合数
        int[] dp = new int[amount + 1];

        /**
         * 2.推导公式：
         * dp[j] += dp[j-coins[i]] 组合数累加
         */

        // 3.初始化
        dp[0] = 1; // 表示凑成总金额为0的方案有1种

        // 4.构建dp（完全背包求组合：先物品后背包+背包正序遍历）
        for (int i = 0; i < coins.length; i++) { // 先物品
            for (int j = coins[i]; j <= amount; j++) { // 后背包(j>=coins[i]条件下需处理)
                dp[j] += dp[j - coins[i]];
            }
        }

        // 结果返回
        return dp[amount];
    }
}
