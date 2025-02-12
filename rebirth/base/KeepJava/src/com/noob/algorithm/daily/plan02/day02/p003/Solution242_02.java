package com.noob.algorithm.daily.plan02.day02.p003;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 */
public class Solution242_02 {
    /**
     * 思路分析：判断t是否为s的字母异位词
     * ① 计数法：每个字母的个数一致匹配
     * ② 排序法：排序后的字母序列完全一致
     */
    public boolean isAnagram(String s, String t) {
        // 长度不同，不可能互为字母异位词
        if (s.length() != t.length()) {
            return false;
        }
        // 定义哈希表存储s的每个字母出现次数（可以用map或者数组（因为字母序列有限可以用数组支持快速访问））
        Map<Character, Integer> map = new HashMap<>(); // map 存储s字符串中每个字符的出现次数
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        // 遍历字符串t序列，校验是否满足字符序列一一匹配
        for (char ch : t.toCharArray()) {
            // 判断字符是否在s(map)中存在
            if (map.containsKey(ch)) {
                int cnt = map.get(ch);
                if (cnt <= 0) {
                    return false; // 存在的字母不足次数
                } else {
                    map.put(ch, cnt - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
