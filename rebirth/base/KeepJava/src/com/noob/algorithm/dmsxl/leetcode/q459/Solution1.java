package com.noob.algorithm.dmsxl.leetcode.q459;

/**
 * 459 重复的子字符串
 */
public class Solution1 {

    // todo 超时
    // 暴力法：找到所有子串，判断其可否由子串重复拼接构成
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) { // j 从i+1开始，确保起码子串非空
                String subStr = s.substring(i, j);
                // 判断子串是否拼接成s
                if (valid(subStr, s)) {
                    return true; // 存在满足条件的子串直接返回true
                }
            }
        }
        // 不存在
        return false;
    }


    public boolean valid(String subStr, String s) {
        /**
         * 获取subStr大小，如果subStr可重复拼接成s应该满足两个条件
         * 1.sLen % subStrLen == 0
         * 2.经过多次拼接后生成的字符串和s完全一致
         */
        int sLen = s.length();
        int subStrLen = subStr.length();
        // 空字符串、子串大于sLen一半（无法通过重复得到s）
        if (subStrLen == 0 || subStrLen > sLen / 2) {
            return false;
        }
        if (sLen % subStrLen != 0) {
            return false;
        }
        int turn = sLen / subStrLen;
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < turn; i++) {
            str.append(subStr);
        }
        return s.equals(str.toString());
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        solution.repeatedSubstringPattern("abab");
    }
}
