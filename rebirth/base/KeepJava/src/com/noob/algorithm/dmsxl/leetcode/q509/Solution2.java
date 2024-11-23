package com.noob.algorithm.dmsxl.leetcode.q509;

/**
 * 509 斐波那契数
 */
public class Solution2 {

    /**
     * 动态规划：空间优化版本
     */
    public int fib(int n) {
        // 特例校验
        if (n == 0 || n == 1) {
            return n;
        }

        int p = 0; // 原dp[i-2]
        int q = 1; // 原dp[i-1]
        int r = 0; // 原dp[i]

        // 4.构建dp（遍历顺序）
        for (int i = 2; i <= n; i++) {
            r = p + q; // 计算dp[i]
            // 变量往前滚动
            p = q;
            q = r;
        }

        // 返回第n个斐波那契数
        return r;
    }
}
