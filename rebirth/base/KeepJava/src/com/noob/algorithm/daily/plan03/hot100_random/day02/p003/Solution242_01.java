package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 * 概要：给定两个字符串s、t 判断t是否为s的字母异位词
 */
public class Solution242_01 {
    /**
     * 思路分析：
     * 统计计数法
     */
    public boolean isAnagram(String s, String t) {
        // 计数思路: 统计每个字符出现的次数，校验是否完全匹配（限定包含小写字母，此处可以用数组来进行处理）
        int[] sCnt = new int[26];
        for (char ch : s.toCharArray()) {
            sCnt[ch - 'a']++; // 统计字符出现次数
        }

        int[] tCnt = new int[26];
        for (char ch : t.toCharArray()) {
            tCnt[ch - 'a']++;
        }

        // 遍历两个数组，校验是否完全一致
        for (int i = 0; i < 26; i++) {
            if (sCnt[i] != tCnt[i]) {
                return false;
            }
        }

        // 校验通过，返回true
        return true;
    }

}
