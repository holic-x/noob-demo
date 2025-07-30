package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 459 重复的子字符串 - https://leetcode.cn/problems/repeated-substring-pattern/description/
 */
public class Solution459_01 {

    /**
     * 校验非空字符串s是否可以由其字串重复多次构成
     * 思路分析：
     * - 技巧法：如果一个字符串可以由子串重复多次构成则满足一个规则 s+s 构成的新字符串中存在一个s(其在中间位置，非首尾)
     */
    public boolean repeatedSubstringPattern(String s) {
        String newStr = s + s;
        return newStr.indexOf(s,1) != s.length();
    }

    public static void main(String[] args) {
        Solution459_01 solution = new Solution459_01();
        solution.repeatedSubstringPattern("abab");
    }

}
