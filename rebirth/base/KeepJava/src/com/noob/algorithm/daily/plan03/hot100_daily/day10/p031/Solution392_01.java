package com.noob.algorithm.daily.plan03.hot100_daily.day10.p031;

/**
 * 🟢 392 判断子序列 -  https://leetcode.cn/problems/is-subsequence/
 */
public class Solution392_01 {

    /**
     * 思路分析：
     * 判断s是否为t的子序列（双指针思路）
     */
    public boolean isSubsequence(String s, String t) {
        int sp = 0, tp = 0;
        int sn = s.length(), tn = t.length();
        while (sp < sn && tp < tn) {
            if (s.charAt(sp) == t.charAt(tp)) {
                sp++;
                tp++;
            } else {
                tp++; // tp 指针前移
            }
        }

        // 校验到sp的最后一个位置
        return sp == sn;
    }
}