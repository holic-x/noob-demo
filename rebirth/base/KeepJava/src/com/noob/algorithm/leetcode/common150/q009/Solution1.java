package com.noob.algorithm.leetcode.common150.q009;

/**
 * 009 回文数
 */
public class Solution1 {

    /**
     * 回文数：逆序都是一样的数字（回归到和回文字符串判断的思路）
     * 正序逆序遍历序列一致、双指针遍历等
     */
    public boolean isPalindrome(int x) {
        String xstr = String.valueOf(x);
        // 获取逆序后的数字
        StringBuffer reverseStr = new StringBuffer();
        for (int i = xstr.length() - 1; i >= 0; i--) {
            reverseStr.append(xstr.charAt(i));
        }
        // 判断正序、逆序是否一致
        return xstr.equals(reverseStr.toString()); // 字符串比较
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.isPalindrome(121));
    }
}
