package com.noob.algorithm.daily.archive.plan02.hot100.day02.p003;

import java.util.Arrays;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 */
public class Solution242_01 {
    /**
     * 思路分析：判断t是否为s的字母异位词
     * ① 计数法：每个字母的个数一致匹配
     * ② 排序法：排序后的字母序列完全一致
     */
    public boolean isAnagram(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        // 排序
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        int sLen = sArray.length;
        int tLen = tArray.length;
        if (sLen != tLen) {
            return false;
        }

        for (int i = 0; i < sLen; i++) {
            if (sArray[i] != tArray[i]) {
                return false;
            }
        }
        return true;
    }

}
