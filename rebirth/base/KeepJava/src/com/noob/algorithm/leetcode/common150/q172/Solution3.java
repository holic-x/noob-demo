package com.noob.algorithm.leetcode.common150.q172;

/**
 * 172 阶乘后的零
 */
public class Solution3 {

    /**
     * 阶乘后的零：非计算阶乘思路
     * 将题目转化为查找【1，n】中5的倍数的个数
     */
//    public int trailingZeroes(int n) {
//        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
//    }


    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        solution3.trailingZeroes(24);
    }
}
