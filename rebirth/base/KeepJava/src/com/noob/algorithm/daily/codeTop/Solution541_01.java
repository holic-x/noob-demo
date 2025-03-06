package com.noob.algorithm.daily.codeTop;

/**
 * 🟢 541 反转字符串II - https://leetcode.cn/problems/reverse-string-ii/description/
 */
public class Solution541_01 {

    /**
     * 思路分析：每计数2k个字符，就反转2k中的前k个字符，如果最终剩余字符少于k则全部反转，如果大于等于k小于2k则反转前k
     */
    public String reverseStr(String s, int k) {
        // 遍历字符串，反转指定区域的字符串
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i += 2 * k) {
            reverse(chs, i, Math.min(i + k - 1, chs.length - 1)); // 注意下标取值
        }
        // 返回反转后的字符串
        return String.valueOf(chs);
    }

    // 反转指定区域的字符串[start,end]
    public void reverse(char[] chs, int start, int end) {
        while (start <= end) {
            // 互换位置
            char temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;
            // 指针移动
            start++;
            end--;
        }


    }


}
