package com.noob.algorithm.dmsxl.leetcode.q279;

/**
 * 279 完全平方数
 */
public class Solution1 {

    /**
     * 完全背包问题
     *
     * @param n 背包容量 | 物品从1-x中取（x*x=n）
     * @return
     */
    public int numSquares(int n) {
        // 1.dp定义(dp[j] 表示凑成数字j的完全平方数的最少数量)
        int[] dp = new int[n + 1];

        /**
         * 2.dp递推
         * dp[j] = Math.min(dp[j],dp[j-i*i]+1)
         */

        // 3.dp初始化
        dp[0] = 0;
        for (int j = 1; j <= n; j++) {
            dp[j] = Integer.MAX_VALUE;  // 其余数组元素初始化为最大值，避免递推过程中被min覆盖 Integer.MAX_VALUE
        }

        // 4.构建dp
        // 先物品后背包容量 + 背包正序
        for (int i = 1; i * i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i * i) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }

        /*
        // 先背包容量后物品 + 背包正序
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i * i <= j; i++) { // i的取值受限于背包容量
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
         */

        // 返回结果
        return dp[n];
    }
}
