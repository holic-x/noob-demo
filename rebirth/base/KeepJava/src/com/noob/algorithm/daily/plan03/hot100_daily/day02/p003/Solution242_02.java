package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 * 概要：给定两个字符串s、t 判断t是否为s的字母异位词
 */
public class Solution242_02 {
    /**
     * 思路分析：
     * 字母异位词：字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次
     * 统计法：所有字母出现次数完全一致
     * 排序法：排序后的字符序列完全一致
     */
    public boolean isAnagram(String s, String t) {
        // 判断字符长度是否均一致，如果此处不做校验则后续还要对可能剩余的sMap补充校验
        if (s.length() != t.length()) {
            return false;
        }

        // 数据统计思路：Map 写法：Map<Character,Integer> map
        Map<Character, Integer> sMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
        }

        // 遍历t字符串字符，校验sMap是否可支撑元素组成
        for (char ch : t.toCharArray()) {
            if (sMap.containsKey(ch)) {
                // 校验当前可用字符数
                int cnt = sMap.get(ch);
                if (cnt == 0) {
                    return false;
                } else {
                    // 剩余可用字符可构成
                    sMap.put(ch, cnt - 1); // 更新可用字符数
                }
            } else {
                return false;
            }
        }

        // 返回结果
        return true; // 校验通过
    }

}
