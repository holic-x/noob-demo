package com.noob.algorithm.plan_archive.plan01.day07;

/**
 * 🟢 028 找出字符串第一个匹配项的下标
 */
public class Solution028_01 {
    /**
     * 在haystack找到第一个匹配needle的字符串的索引位置
     */
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        int first = needle.charAt(0);
        // 先找到每一个起点相同的位置，然后校验基于这个起点是否满足
        for (int i = 0; i < hLen; i++) {
            // 校验首字母是否匹配
            if (haystack.charAt(i) == first) {
                int end = Math.min(i + nLen, hLen); // 限定校验字符串（注意越界问题）
                String subStr = haystack.substring(i, end);
                if (subStr.equals(needle)) {
                    return i;
                }
            }
        }
        // 没有找到匹配的内容
        return -1;
    }
}
