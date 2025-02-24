package com.noob.algorithm.solution_archive.dmsxl.leetcode.q242;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 有效的字母异位词（242）
 */
public class Solution2 {
    // 【2】排序法
    public boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不一致则必然非字母异位词
        if(s.length() != t.length()){
            return false;
        }
        // 分别对两个字符串序列进行排序，判断排序后的字符串序列是否完全一致
        char[] sArr = s.toCharArray();
        Arrays.sort(sArr);
        char[] tArr = t.toCharArray();
        Arrays.sort(tArr);
        // 校验
        return String.valueOf(sArr).equals(String.valueOf(tArr));
        // return Arrays.equals(sArr, tArr);
    }
}
