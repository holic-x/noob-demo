package com.noob.algorithm.daily.codeTop;


/**
 * 🟡 518 零钱兑换II - https://leetcode.cn/problems/coin-change-ii/
 */
public class Solution518_01 {


    /**
     * 动态规划思路：
     */
    public int change(int amount, int[] coins) {
        int m = coins.length, n = amount + 1;
        // 1.dp 定义: dp[j] 表示可以用[0,i]中的硬币凑成总金额的硬币组合数
        int[] dp = new int[n];

        /**
         * 2.dp 递推
         * dp[j] = dp[j] + dp[j-nums[i]]
         * 对于每个物品（硬币），可以选择加入或者不加入：
         * - 选择加入：继承状态
         * - 选择不加入：空出当前硬币值
         * 两种方案累加则可得到方案数
         */

        // 3.dp 初始化
        dp[0] = 1; // 凑成总金额为0的硬币组合数有1种

        // 4.dp 构建
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }

        // 判断硬币是否可以构成总金额
        return dp[amount];
    }
}
