package com.noob.algorithm.daily.plan03.hot100_template.day08.p025;

/**
 * 🟢 070 爬楼梯 - https://leetcode.cn/problems/climbing-stairs/
 */
public class Solution070_01 {

    /**
     * 思路分析：有多少种不同的方案爬到楼顶
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }
        // dp[i] 表示爬到第i阶有多少种方案
        int[] dp = new int[n + 1];
        dp[0] = 1; // 不爬
        dp[1] = 1; // 爬1层

        for (int i = 2; i <= n; i++) {
            // 第i-1阶爬1层 + 第i-2阶爬2层
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回结果
        return dp[n];
    }

}
