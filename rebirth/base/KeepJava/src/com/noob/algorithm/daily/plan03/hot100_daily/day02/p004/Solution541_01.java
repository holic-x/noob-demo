package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 541 反转字符串II - https://leetcode.cn/problems/reverse-string-ii/description/
 */
public class Solution541_01 {
    /**
     * 概要：给定字符串s和整数k，每计数至2k个字符就反转其前k个字符，如果剩余字符少于k个则反转全部剩余字符，如果剩余字符在[k,2k]则反转前k个字符
     * 思路分析：
     * - 此处注意步长和反转范围处理，且不要频繁进行String、char[]互转，先转为char[]处理数组反转，随后将反转后的结果处理为String，整个过程只需要转化2次，而不是每反转一遍、处理一遍.
     */
    public String reverseStr(String s, int k) {
        // 2K 步长
        for (int i = 0; i < s.length(); i += 2 * k) {
            // 处理前K个字符：[0,min{i+k,sLen})
            s = reverse(s, i, Math.min(i + k, s.length()) - 1);
        }

        return s;
    }

    // 字符串反转方法（部分区域反转概念：字符串反转）:反转范围[start,end]
    private String reverse(String s, int start, int end) {
        char[] chs = s.toCharArray();
        while (start <= end) {
            char tmp = chs[start];
            chs[start] = chs[end];
            chs[end] = tmp;
            start++;
            end--;
        }
        // 返回反转结果
        return new String(chs);
    }

    public static void main(String[] args) {

        Solution541_01 solution = new Solution541_01();
        solution.reverseStr("abcdefg", 2); // res:bacdfeg

    }
}
