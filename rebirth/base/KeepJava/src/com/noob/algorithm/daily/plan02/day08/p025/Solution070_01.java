package com.noob.algorithm.daily.plan02.day08.p025;

/**
 * 🟢 070 爬楼梯 - https://leetcode.cn/problems/climbing-stairs/
 */
public class Solution070_01 {

    /**
     * 思路分析：每层只能爬1或2台阶，有多少种不同的方法可以爬到楼顶
     */
    public int climbStairs(int n) {
        // 1.dp 定义：dp[i] 表示爬到第i阶有多少种方法
        int[] dp = new int[n + 1];
        /**
         * 2.dp 递推
         * dp[i] 可以是第i-2爬2层到i，也可以是第i-1爬1层到i
         * =》dp[i] = dp[i-2] + dp[i-1]
         */

        // 3.dp 初始化
        dp[0] = 1;
        dp[1] = 1;

        // 4.dp 构建
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回爬到第n阶的方案
        return dp[n];
    }

}
