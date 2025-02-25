package com.noob.algorithm.daily.day13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 🔴 076 最小覆盖子串 - https://leetcode.cn/problems/minimum-window-substring/
 */
public class Solution076_01 {

    /**
     * 如果s的子串覆盖了t中所有字符，求出满足这个条件的最小子串
     * 如果不存在覆盖t的所有字符的子串则返回空字符串
     * 思路分析：模拟法（遍历s的所有子串，校验其是否包括t中的所有字符，如果满足则返回即可）
     */
    public String minWindow(String s, String t) {

        int minLen = Integer.MAX_VALUE;
        String minStr = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String subStr = s.substring(i, j + 1);
                // 如果subStr长度小于t，无需校验
                if (subStr.length() < t.length()) {
                    continue;
                }

                if (isValid(subStr, t)) {
                    if (subStr.length() < minLen) {
                        minLen = subStr.length();
                        minStr = subStr;
                    }
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : minStr;

    }


    /**
     * 校验字符串s中是否涵盖t中的所有字符
     * (此处校验是否完全覆盖，不能单纯判断字符是否存在，而是要用s的字符来组成t)
     */
    private boolean isValid(String s, String t) {
        // 哈希
        Map<Character, Integer> map = new HashMap<>(); // 存储s字符串的字符及出现次数
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // 校验
        for (char ch : t.toCharArray()) {
            // 从map中取出字符进行校验
            if (!map.containsKey(ch)) {
                return false;
            } else {
                int curCnt = map.get(ch);
                if (curCnt <= 0) {
                    return false;
                }
                // 取用字符
                map.put(ch, curCnt - 1);
            }
        }

        return true;
    }
    /*
    private boolean isValid(String s, String t) {
        // 哈希
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        // 校验
        for (char ch : t.toCharArray()) {
            if (!set.contains(ch)) {
                return false;
            }
        }
        return true;
    }
     */

    public static void main(String[] args) {
        String s = "a", t = "a";
        Solution076_01 sl = new Solution076_01();
        sl.minWindow(s, t);
    }

}
