package com.noob.algorithm.plan_archive.plan02.hot100.day08.p025;

/**
 * 🟢 509 斐波那契数列 - https://leetcode.cn/problems/fibonacci-number/description/
 */
public class Solution509_01 {

    public int fib(int n) {
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
        // 返回f(n)
        return r;
    }
}
