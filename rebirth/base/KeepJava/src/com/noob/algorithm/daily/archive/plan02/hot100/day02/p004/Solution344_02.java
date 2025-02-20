package com.noob.algorithm.daily.archive.plan02.hot100.day02.p004;

/**
 * 🟢 344 反转字符串 - https://leetcode.cn/problems/reverse-string/description/
 */
public class Solution344_02 {

    /**
     * 思路分析：双指针遍历，两两交换（原地）
     */
    public void reverseString(char[] s) {
        int n = s.length;
        int start = 0, end = n - 1;
        while (start <= end) {
            // 交换元素
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            // 指针移动
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Solution344_02 solution = new Solution344_02();
        solution.reverseString("hello".toCharArray());
    }
}
