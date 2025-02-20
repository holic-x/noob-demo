package com.noob.algorithm.leetcode.common150.q069;

/**
 * 069 x 的平方根
 */
public class Solution1 {

    /**
     * 不能使用内置函数求解：硬核思路就是遍历每个自然数的平方，然后得到最接近目标数的
     * 但需注意用long类型处理数据，否则可能出现数值溢出问题
     */
    public int mySqrt(int x) {
        long res = 0;
        for (long i = 0; i * i <= x; i++) {
            res = i;
        }
        return (int) res;
    }
}
