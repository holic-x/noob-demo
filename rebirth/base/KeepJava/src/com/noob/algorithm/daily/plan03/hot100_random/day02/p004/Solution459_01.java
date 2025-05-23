package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

/**
 * 🟢 459 重复的子字符串 - https://leetcode.cn/problems/repeated-substring-pattern/description/
 */
public class Solution459_01 {

    /**
     * 校验非空字符串s是否可以由其字串重复多次构成
     * 思路分析：
     */
    public boolean repeatedSubstringPattern(String s) {
        StringBuffer buffer = new StringBuffer(s);
        buffer.append(s);
        return buffer.indexOf(s, 1) != s.length();
    }

    public static void main(String[] args) {
        Solution459_01 solution = new Solution459_01();
        solution.repeatedSubstringPattern("abab");
    }

}
