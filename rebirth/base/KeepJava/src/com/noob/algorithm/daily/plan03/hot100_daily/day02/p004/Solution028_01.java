package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 028 找出字符串中第一个匹配项的下标 - https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */
public class Solution028_01 {

    /**
     * 思路分析：
     * 在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1
     * - 在haystack中依次匹配起点字符，校验截断字符串是否与needle匹配，如果匹配则存在，遍历结束未找到符合的字符则返回-1
     */
    public int strStr(String haystack, String needle) {
        int nLen = needle.length();

        int startCh = needle.charAt(0);

        for (int i = 0; i < haystack.length(); i++) {
            char curCh = haystack.charAt(i);
            if (startCh == curCh) {
                // 首字符匹配，校验子字符串是否匹配
                String subStr = haystack.substring(i, Math.min(nLen + i, haystack.length()));
                if (subStr.equals(needle)) {
                    // 寻找到匹配的数据
                    return i;
                }
            }
        }
        // 遍历结束无满足数据
        return -1;
    }

    public static void main(String[] args) {
        Solution028_01 solution = new Solution028_01();
        solution.strStr("abc", "c");
    }

}
