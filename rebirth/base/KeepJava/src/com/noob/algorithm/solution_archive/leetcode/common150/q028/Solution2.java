package com.noob.algorithm.solution_archive.leetcode.common150.q028;

/**
 * 028 找出字符串中第一个匹配项的下标
 */
public class Solution2 {

    /**
     * 思路：投机取巧法（直接用工具方法）
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
