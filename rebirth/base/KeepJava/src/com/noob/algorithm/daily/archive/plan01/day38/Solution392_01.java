package com.noob.algorithm.daily.archive.plan01.day38;

/**
 * 🟢 392 判断子序列 - https://leetcode.cn/problems/is-subsequence/description/
 */
public class Solution392_01 {
    /**
     * 思路：双指针概念(判断s是否为t的子序列)
     */
    public boolean isSubsequence(String s, String t) {
        // 获取字符串长度
        int sLen = s.length(), tLen = t.length();
        // 定义遍历指针
        int sp = 0, tp = 0;
        // 首字符匹配则依次校验其他字符
        while (sp < sLen && tp < tLen) {
            if (s.charAt(sp) == t.charAt(tp)) { // 如果指针指向位置字符相同，则两者继续指向下一位进行校验
                sp++;
                tp++;
            } else {
                tp++; // 如果指针指向位置字符不同，则移动tp寻找下一个匹配字符，直到所有字符校验完成
            }
        }
        return sp == sLen;
    }
}
