package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

/**
 * 🟢 028 找出字符串中第一个匹配项的下标 - https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */
public class Solution028_01 {

    /**
     * 思路分析：在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1
     */
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        char firstCh = needle.charAt(0);

        for (int i = 0; i < hLen; i++) {
            char hCh = haystack.charAt(i);
            // 如果找到第1个匹配的字符，则截取指定位置的字符串进行校验，否则继续往后移动
            if (hCh == firstCh) {
                // 截取指定长度的字符串（注意长度越界问题）
                String splitStr = haystack.substring(i, Math.min(i + nLen, hLen));
                if (splitStr.equals(needle)) {
                    return i; // 找到匹配的字符串，返回满足条件的坐标
                }
            }
        }
        // 没有满足条件的结果
        return -1;
    }

    public static void main(String[] args) {
        Solution028_01 solution = new Solution028_01();
        int ans = solution.strStr("abc", "c");
        System.out.println(ans);
    }

}
