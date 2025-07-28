package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 344 反转字符串 - https://leetcode.cn/problems/reverse-string/description/
 */
public class Solution344_01 {

    /**
     * 思路分析：
     * - 非原地算法：逆序排序
     */
    public void reverseString(char[] s) {
        int n = s.length;
        // 定义结果集合
        char[] ans = new char[n];
        int pt = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans[pt++] = s[i];
        }
        // 返回逆序结果（回填原数组）
        for (int i = 0; i < n; i++) {
            s[i] = ans[i];
        }
    }

    public static void main(String[] args) {
        Solution344_01 solution = new Solution344_01();
        solution.reverseString("hello".toCharArray());
    }
}
