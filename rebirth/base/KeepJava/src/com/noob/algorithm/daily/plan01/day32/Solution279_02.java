package com.noob.algorithm.daily.plan01.day32;

/**
 * 🟡 279-完全平方数 - https://leetcode.cn/problems/perfect-squares/
 */
public class Solution279_02 {

    /**
     * 思路分析：问题转化为完全背包问题，类似凑成指定硬币金额的最少硬币数
     * 此处物品价值和重量即数字（1-x）的取值（此处x的取值取决于n的算数平方根），背包容量为n
     */
    public int numSquares(int n) {
        // 1.dp 定义：dp[i]表示可凑成完全平方数i的最少数字个数
        int[] dp = new int[n + 1];

        /**
         * 2.dp 递推：dp[j] = min{dp[j],dp[j-i*i] + 1}
         */

        // 3.dp 初始化
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE; // 其余数组元素初始化为最大值，避免递推过程中被min覆盖
        }

        // 4.dp 构建：确定遍历顺序（先背包后物品）
        for (int i = 1; i <= n; i++) { // 背包容量受限于n
            for (int j = 1; j * j <= i; j++) { // 物品个数受限于当前背包容量
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        // 返回结果
        return dp[n];
    }

}
