package com.noob.algorithm.leetcode.common150.q058;

/**
 * 058 最后一个单词的长度
 */
public class Solution1 {
    // 思路：字符串切割，取最后一个字母
    public int lengthOfLastWord(String s) {
        String[] splitStr = s.split(" ");
        return splitStr[splitStr.length - 1].length();
    }
}
