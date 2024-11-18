package com.noob.algorithm.dmsxl.leetcode.q028;

public class Solution1 {
    /**
     * 返回needle在haystack中出现的第1个位置
     */
    public int strStr(String haystack, String needle) {
        // return haystack.indexOf(needle);
        // 遍历字符串
        int hLen = haystack.length();
        char firstCh = needle.charAt(0);
        int nLen = needle.length();
        for (int i = 0; i < hLen; i++) {
            // 如果首字符匹配则开始校验
            if (haystack.charAt(i) == firstCh) {
                // 截取字符串进行验证，如果有匹配的直接返回（返回第1个匹配的内容）
                if (i + nLen <= hLen) {
                    if (haystack.substring(i, i + nLen).equals(needle)) {
                        return i;
                    }
                }
            }
        }
        // 没有匹配的内容
        return -1;
    }
}
