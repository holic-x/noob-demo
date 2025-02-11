package com.noob.algorithm.daily.plan02.day02;

/**
 * 🟢 028 找出字符串中第一个匹配项的下标 - https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */
public class Solution028_01 {

    /**
     * 思路分析：在haystack中找出第一个匹配needle序列的下标，如果needle不是haystack的一部分则返回-1
     */
    public int strStr(String haystack, String needle) {
        // 双指针遍历校验：外层指针i确定起点，内层指针j用于校验序列是否匹配
        for (int i = 0; i < haystack.length(); i++) {
            // 如果元素与needle首元素相同则进一步校验
            if (haystack.charAt(i) == needle.charAt(0)) {
                // 以i为起点，遍历序列是否匹配（截取字符串进行校验）
                if (i + needle.length() <= haystack.length()) { // 注意有效的长度取值范围，处理临界问题
                    String subStr = haystack.substring(i, i + needle.length());
                    if (subStr.equals(needle)) {
                        return i; // 字符串匹配，返回匹配的第1个下标
                    }
                } else {
                    break; // 后面无法构成needle的长度，无需继续校验
                }
            }
        }
        return -1;
    }

    // 校验两个字符串是否匹配(直接用equals)
    private boolean validStr(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        if (len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        // 匹配
        return true;
    }

    public static void main(String[] args) {
        Solution028_01 solution = new Solution028_01();
        solution.strStr("abc", "c");
    }

}
