package com.noob.algorithm.plan_archive.plan01.day04;

import java.util.Arrays;

/**
 * 🟢242. 有效的字母异位词
 */
public class Solution242_01 {
    // 字母异位词：排序后的字母序列一致
    public boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        // 如果两者长度不一致则不可能为字母异位词
        if (sArr.length != tArr.length) {
            return false;
        }
        // 遍历校验两个字符串排序后的序列是否完全一致
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return false; // 出现不一致
            }
        }

        // 遍历到尾部说明序列一致
        return true;
    }
}
