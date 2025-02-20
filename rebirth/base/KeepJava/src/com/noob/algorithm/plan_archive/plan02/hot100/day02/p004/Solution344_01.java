package com.noob.algorithm.plan_archive.plan02.hot100.day02.p004;

/**
 * 🟢 344 反转字符串 - https://leetcode.cn/problems/reverse-string/description/
 */
public class Solution344_01 {

    /**
     * 思路分析：逆序遍历（非原地,需借助辅助数组）
     */
    public void reverseString(char[] s) {
        int n = s.length;
        char[] res = new char[n];
        int cur = 0;
        for (int i = n - 1; i >= 0; i--) {
            res[cur++] = s[i];
        }
        // s = res;

        for(int i=0;i<n;i++){
            s[i] = res[i];
        }
    }

    public static void main(String[] args) {
        Solution344_01 solution = new Solution344_01();
        solution.reverseString("hello".toCharArray());
    }
}
