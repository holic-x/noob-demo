package com.noob.algorithm.daily.archive.plan01.day04;

import java.util.Arrays;

/**
 * 🟢242. 有效的字母异位词
 */
public class Solution242_02 {
    /**
     * 字母异位词：所有字母的使用次数相同，可遍历统计s的字符对应的出现次数，然后遍历t进行校验
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        // 判断两个字符串长度是否一致，如果不一致则不可能为字母异位词
        if (s.length() != t.length()) {
            return false;
        }

        // 此处定义数组存储26个字母对应的出现次数（也可使用map）：'x' - 'a' => 与数组下标对照
        int[] cnt = new int[26];
        // 遍历s的字符序列，统计每个字符出现次数
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // 遍历t的字符序列，同步校验cnt中的字符出现次数
        for (int i = 0; i < t.length(); i++) {
            cnt[t.charAt(i) - 'a']--;
            // 校验是否出现负数，一旦出现负数说明不匹配
            if (cnt[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
