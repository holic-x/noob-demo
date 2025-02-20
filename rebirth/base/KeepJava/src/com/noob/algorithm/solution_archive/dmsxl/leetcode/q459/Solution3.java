package com.noob.algorithm.solution_archive.dmsxl.leetcode.q459;

/**
 * 459 重复的子字符串
 */
public class Solution3 {

    // 规律法：判断字符串是否由重复子字符串组成，则只需要将两个s拼接在一起，如果里面还出现1个s（除了首尾）的话，则说明其由重复子串组成
    public boolean repeatedSubstringPattern(String s) {
        return (s+s).indexOf(s,1) != s.length();
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        solution.repeatedSubstringPattern("abab");
    }
}
