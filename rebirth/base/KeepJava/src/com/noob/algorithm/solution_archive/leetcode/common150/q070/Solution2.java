package com.noob.algorithm.solution_archive.leetcode.common150.q070;

/**
 * 070 爬楼梯
 */
public class Solution2 {
    // 动态规划（空间优化版本）
    public int climbStairs(int n) {
        // 1.定义滚动数组要素
        int p = 1; // 前N-2阶
        int q = 1; // 前N-1阶
        int r = 0; // N阶方案计数

        // 需对临界条件进行额外处理
        if(n==0 || n== 1){
            return 1;
        }

        // 2.根据公式计算第N阶的方案数，并滚动记录
        for(int i=2;i<=n;i++){
            // 公式：爬N阶的方案=爬N-1的方案+爬N-2的方案
            r = p + q;
            // 滚动记录
            p = q;
            q = r;
        }
        // 3.返回结果
        return r;
    }
}
