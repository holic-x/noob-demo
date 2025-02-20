package com.noob.algorithm.daily.archive.plan02.hot100.day02.p003;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟢 383 赎金信 - https://leetcode.cn/problems/ransom-note/description/
 */
public class Solution383_01 {

    /**
     * 思路分析：看ransomNote中的字符序列可否由magazine中提供的字符构成
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 计数思路：统计magazine中出现的字符次数，然后遍历ransomNote序列看是否可构成
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        // 校验ransomNote中的字符序列是否由map提供
        for (char ch : ransomNote.toCharArray()) {
            if (map.containsKey(ch)) {
                // 校验当前字符剩余可用次数
                int cnt = map.get(ch);
                if (cnt <= 0) {
                    return false;
                } else {
                    map.put(ch, cnt - 1);
                }
            } else {
                return false;
            }
        }
        // 满足条件
        return true;
    }

}
