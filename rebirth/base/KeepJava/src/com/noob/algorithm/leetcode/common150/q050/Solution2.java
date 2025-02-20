package com.noob.algorithm.leetcode.common150.q050;

/**
 * q50 Pow(x,n)
 */
public class Solution2 {
    /**
     * 递归计算 y = x ^n/2^
     * - 如果n为偶数则 x ^n^ = y * y
     * - 如果n为奇数则 x ^n^ = y * y * x
     */
    public double myPow(double x, int n) {
        return n >= 0 ? quickly(x, n) : (1.0 / quickly(x, -n));
    }

    /**
     * 递归计算 x^n^
     */
    public double quickly(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = quickly(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }
}
