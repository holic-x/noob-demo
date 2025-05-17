package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

/**
 * 🟢 344 反转字符串 - https://leetcode.cn/problems/reverse-string/description/
 */
public class Solution344_02 {

    /**
     * 思路分析：双指针遍历，两两交换（原地）
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left <= right) {
            // 交换元素
            char ch = s[left];
            s[left] = s[right];
            s[right] = ch;
            // 交换完成，指针移动
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution344_02 solution = new Solution344_02();
        solution.reverseString("hello".toCharArray());
    }
}
