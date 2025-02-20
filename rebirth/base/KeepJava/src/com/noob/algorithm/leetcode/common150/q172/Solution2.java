package com.noob.algorithm.leetcode.common150.q172;

/**
 * 172 阶乘后的零
 */
public class Solution2 {

    /**
     * 阶乘后的零：非计算阶乘思路
     * 将题目转化为查找【1，n】中5的倍数的个数
     */
    public int trailingZeroes(int n) {
        // 统计尾随0
        int count = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                count++;
            }
        }
        return count;
    }
}
