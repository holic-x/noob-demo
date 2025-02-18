package com.noob.algorithm.daily.archive.plan02.day08.p025;

/**
 * 🟢 070 爬楼梯 - https://leetcode.cn/problems/climbing-stairs/
 */
public class Solution070_02 {

    /**
     * 思路分析：每层只能爬1或2台阶，有多少种不同的方法可以爬到楼顶
     * - 版本优化：空间优化版本
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int p = 1, q = 1, r = 0;
        // 基于滚动变量优化
        for (int i = 2; i <= n; i++) {
            r = p + q;
            // 滚动变量
            p = q;
            q = r;
        }

        // 返回爬到第n阶的方案
        return r;
    }

}
