package com.noob.algorithm.daily.plan03.hot100_daily.day08.p025;

/**
 * 🟢 509 斐波那契数列 - https://leetcode.cn/problems/fibonacci-number/description/
 */
public class Solution509_02 {

    /**
     * 思路分析：动态规划 空间优化版本
     */
    public int fib(int n) {
        // 特例判断
        if (n == 0 || n == 1) {
            return n;
        }

        int p = 0, q = 1, r = 0;
        for (int i = 2; i <= n; i++) {
            r = p + q;
            // 滚动变量
            p = q;
            q = r;
        }
        return r;
    }
}
