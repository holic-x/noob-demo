package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.Arrays;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 * 概要：给定两个字符串s、t 判断t是否为s的字母异位词
 */
public class Solution242_02 {
    /**
     * 思路分析：
     * 排序法
     */
    public boolean isAnagram(String s, String t) {
        char[] sChs = s.toCharArray();
        char[] tChs = t.toCharArray();
        Arrays.sort(sChs);
        Arrays.sort(tChs);
        // 校验两个排序后的内容是否一致
        // return sChs.toString().equals(tChs.toString());
        String sortS = String.valueOf(sChs);
        String sortT = String.valueOf(tChs);
        return sortS.equals(sortT);
    }

}
