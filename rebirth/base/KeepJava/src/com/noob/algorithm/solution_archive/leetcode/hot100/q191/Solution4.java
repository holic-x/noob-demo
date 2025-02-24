package com.noob.algorithm.solution_archive.leetcode.hot100.q191;

/**
 * 191 位1的个数
 */
public class Solution4 {

    // 位运算
    public int hammingWeight(int n) {
        // 通过 n&(n-1) 移出最右边的1
        int res = 0;
        while (n != 0) {
            n = n & (n - 1); // n 不为0，每次移出最右边的1，然后进行统计
            res++;
        }
        return res;
    }

}
