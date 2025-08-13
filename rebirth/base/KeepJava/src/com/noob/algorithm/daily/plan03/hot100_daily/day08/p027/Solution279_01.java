package com.noob.algorithm.daily.plan03.hot100_daily.day08.p027;

import java.util.Arrays;

/**
 * 🟡 279 完全平方数 - https://leetcode.cn/problems/perfect-squares/description/
 */
public class Solution279_01 {

    /**
     * 思路分析：
     */
    public int numSquares(int n) {

        // dp[i] 凑满i的完全平方数的最少数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i * i) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }

        return dp[n];
    }
}
