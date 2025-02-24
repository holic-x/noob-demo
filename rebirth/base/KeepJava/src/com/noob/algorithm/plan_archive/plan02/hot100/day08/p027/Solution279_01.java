package com.noob.algorithm.plan_archive.plan02.hot100.day08.p027;

/**
 * 🟡 279 完全平方数 - https://leetcode.cn/problems/perfect-squares/description/
 */
public class Solution279_01 {

    /**
     * 思路分析：返回和为 n 的完全平方数的最少数量
     * 例如 12 = 4+4+4
     */
    public int numSquares(int n) {
        // 1.dp 定义：dp[j] 表示[0,n]个数中凑成和为j的元素的最少数量
        int[] dp = new int[n + 1];

        /**
         * 2.dp 递推
         * dp[j] = min{dp[j],dp[j-i*i]}
         */

        // 3.dp 初始化
        dp[0] = 0;
        for (int j = 1; j <= n; j++) {
            dp[j] = Integer.MAX_VALUE;  // 其余数组元素初始化为最大值，避免递推过程中被min覆盖 Integer.MAX_VALUE
        }

        // 4.dp 构建（遍历顺序：物品/背包 不限制顺序，正序遍历）
        for (int i = 1; i * i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i * i) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }

        // 返回结果
        return dp[n];
    }
}
