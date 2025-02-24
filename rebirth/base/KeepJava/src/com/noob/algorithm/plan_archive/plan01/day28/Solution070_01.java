package com.noob.algorithm.plan_archive.plan01.day28;

/**
 * 🟢 070 爬楼梯 - https://leetcode.cn/problems/climbing-stairs/description/
 */
public class Solution070_01 {
    /**
     * 动态规划思路
     */
    public int climbStairs(int n) {
        // 1.dp 定义（dp[i]表示爬到第i阶共有多少种方案）
        int[] dp = new int[n + 1]; // 此处要计算到n，数组长度取值为n+1

        /**
         * 2.dp 递推
         * 爬n阶的方案可以基于爬n-1阶方案基础上再爬1阶或者是爬n-2阶方案再爬2阶得到，因此递推公式有f(n) = f(n-1) + f(n-2)
         */

        // 3.dp 初始化
        dp[0] = 1; // 爬0阶意味着原地不动，可以理解为也是一种方案作为递推基础
        dp[1] = 1; // 爬1阶共有1种方案

        // 4.dp 构建
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回爬n阶的方案数
        return dp[n];
    }


}
