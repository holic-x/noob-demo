package com.noob.algorithm.dmsxl.leetcode.q509;

import java.util.Arrays;

/**
 * 509 斐波那契数
 */
public class Solution1 {

    public int fib(int n) {
        // 特例校验
        if (n == 0 || n == 1) {
            return n;
        }

        // 1.定义dp
        int[] dp = new int[n + 1]; // dp[i]表示前两个数之和(i>2)

        /**
         * 2.推导公式
         * dp[0]=0,dp[1]=1,dp[i]=dp[i-1]+dp[i-2]
         */

        // 3.dp初始化
        dp[0] = 0;
        dp[1] = 1;

        // 4.构建dp（遍历顺序）
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回第n个斐波那契数
        return dp[n];
    }
}
