package com.noob.algorithm.dmsxl.leetcode.q242;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 有效的字母异位词（242）
 */
public class Solution12 {

    // 【1】计数法（定义字符序列的出现次数集合：使用数组实现）
    public boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不一致则必然非字母异位词
        if (s.length() != t.length()) {
            return false;
        }

        // 1.定义数组存放`a`-`z`字符序列的出现次数
        int[] letterNum = new int[26];
        // 2.遍历字符串s统计字符出现次数
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i); // 此时cur为字符形式，需转化为数组下标存储：'a'对应 0 需进行转化则需减去'a'
            letterNum[cur - 'a']++; // 累加统计
        }
        // 3.遍历字符串t，从目前现有的字符集合中取出元素，如果集合中没有足够的次数，说明不匹配
        for (int i = 0; i < t.length(); i++) {
            char cur = t.charAt(i);
            if (letterNum[cur - 'a'] <= 0) {
                return false; // 次数不足说明不匹配
            } else {
                letterNum[cur - 'a']--; // 表示使用该字符一次
            }
        }

        // 校验通过
        return true;
    }

}
