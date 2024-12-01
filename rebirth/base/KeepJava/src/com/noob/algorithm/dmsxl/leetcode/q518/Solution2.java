package com.noob.algorithm.dmsxl.leetcode.q518;

/**
 * 518 零钱兑换
 */
public class Solution2 {
    // 动态规划（二维数组版本）
    public int change(int amount, int[] coins) {
        int m = coins.length;
        // 1.dp[i][j]: [0-i]的硬币凑成总金额为j的硬币组合数
        int[][] dp = new int[m][amount + 1];

        /**
         * 2.推导公式：
         *  - j<coins[i]: dp[i][j] = dp[i-1][j]
         *  - j>=coins[i]: dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]]
         */

        // 3.初始化(首行、首列初始化)
        // 首列初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 凑成金额为0的方案只有一种
        }
        // 首行初始化：只用硬币coins[0]凑成金额j
        for (int j = 0; j <= amount; j++) {
            // 金额j对coins[0]求余如果为0得到1种方案
            if (j % coins[0] == 0) {
                dp[0][j] = 1;
            }
        }

        // 4.构建dp（完全背包求组合：先物品后背包+背包正序遍历）
        for (int i = 1; i < m; i++) { // 先物品
            for (int j = 1; j <= amount; j++) { // 后背包(j>=coins[i]条件下需处理)
                if (j < coins[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                }
            }
        }

        // 结果返回
        return dp[m - 1][amount];
    }
}
