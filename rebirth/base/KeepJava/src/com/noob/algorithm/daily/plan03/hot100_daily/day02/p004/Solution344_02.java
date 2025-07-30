package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 344 反转字符串 - https://leetcode.cn/problems/reverse-string/description/
 */
public class Solution344_02 {

    /**
     * 思路分析：
     * - 原地算法：双指针思路
     */
    public void reverseString(char[] s) {
        // 分别定义双指针
        int n = s.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            // 交换位置
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            // 处理完成，指针移动
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution344_02 solution = new Solution344_02();
        solution.reverseString("hello".toCharArray());
    }
}
