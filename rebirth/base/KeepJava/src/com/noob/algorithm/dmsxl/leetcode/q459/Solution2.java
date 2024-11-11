package com.noob.algorithm.dmsxl.leetcode.q459;

/**
 * 459 重复的子字符串
 */
public class Solution2 {

    /**
     * 前缀+倍数+规律
     * 重复子串的满足条件：
     * 1.前缀
     * 2.倍数（subStr的长度是s的长度的倍数）
     * 3.对于任意的 i∈[n',n)，有 s[i]=s[i−n']
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        // 限定i为子串的长度（从1-n/2）
        for (int i = 1; i <= n / 2; i++) {
            // 只是校验满足倍数关系的子串
            if (n % i == 0) { // i 为当前设定的子串长度
                // 校验是否满足i∈[n',n)，有 s[i]=s[i−n']
                boolean match = true;
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false; // 一旦不匹配无需后续校验直接进行下一个长度的判断
                        break;
                    }
                }
                // 校验通过返回true
                if (match) { // 找到匹配直接返回
                    return true;
                }
            }
        }
        // 其他情况无匹配
        return false;
    }


    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        solution.repeatedSubstringPattern("abab");
    }
}
