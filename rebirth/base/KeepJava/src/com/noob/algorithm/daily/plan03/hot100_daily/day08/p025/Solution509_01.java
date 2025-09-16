package com.noob.algorithm.daily.plan03.hot100_daily.day08.p025;

/**
 * 🟢 509 斐波那契数列 - https://leetcode.cn/problems/fibonacci-number/description/
 */
public class Solution509_01 {

    /**
     * 思路分析：
     */
    public int fib(int n) {
        // 特例判断
        if (n == 0 || n == 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
