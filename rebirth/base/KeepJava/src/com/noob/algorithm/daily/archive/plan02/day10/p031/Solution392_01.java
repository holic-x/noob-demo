package com.noob.algorithm.daily.plan02.day10.p031;

/**
 * 🟢 392 判断子序列 -  https://leetcode.cn/problems/is-subsequence/
 */
public class Solution392_01 {

    /**
     * 判断s是否为t的子序列，基于上指针思路校验
     */
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int sp = 0, tp = 0;
        while (sp < sLen && tp < tLen) {
            // 校验两个指针指向位置的元素是否相同
            if (s.charAt(sp) == t.charAt(tp)) {
                sp++;
                tp++;
            } else {
                // 如果不相同，则继续指向下一个校验位置
                tp++;
            }
        }
        return sp == sLen;
    }
}