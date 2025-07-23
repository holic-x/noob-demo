package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.Arrays;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 * 概要：给定两个字符串s、t 判断t是否为s的字母异位词
 */
public class Solution242_01 {
    /**
     * 思路分析：
     * 字母异位词：字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次
     * 统计法：所有字母出现次数完全一致
     * 排序法：排序后的字符序列完全一致
     */
    public boolean isAnagram(String s, String t) {

        // 排序
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        // 验证排序结果
        return new String(sArr).equals(new String(tArr));
    }

}
