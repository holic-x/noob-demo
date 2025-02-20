package com.noob.algorithm.solution_archive.leetcode.common150.q151;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151 反转字符串中的单词
 */
public class Solution2 {
    /**
     * 思路分析：字符串切割思路
     * 字符串切割（split） + 反转（reverse） + 拼接（join）
     */
    public String reverseWords(String s) {

        // 1.切割字符串（注意首尾空格和连续空格的处理）
        String[] words = s.trim().split("\\s+");

        // 2.反转数组
        List<String> list = Arrays.asList(words);
        Collections.reverse(list);

        // 3.拼接返回
        return String.join(" ", list);
    }
}
