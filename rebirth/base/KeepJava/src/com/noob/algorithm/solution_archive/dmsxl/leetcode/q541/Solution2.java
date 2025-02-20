package com.noob.algorithm.solution_archive.dmsxl.leetcode.q541;

import java.util.Arrays;

/**
 * 541 反转字符串II
 */
public class Solution2 {

    // 思路：根据限定条件反转字符串（转化为字符数组处理）
    public String reverseStr(String s, int k) {
        // 将字符串转化为字符数组
        char[] arr = s.toCharArray();
        // 判断s与2k的关系，确定反转规则
        int len = s.length();

        // 反转指定索引范围的元素(0-k,2k-3k,......)
        for (int i = 0; i < len; i += 2 * k) {
            /**
             * 此处可以拆分为两部分：一部分是2k范围内的数据（k>=0），一部分是2k范围外的数据
             * 两种场景的起点start其实都是一样的（0、2k、4k），而终点的取值则要看数组元素的长度大小
             * - 对于2k范围内的取值，end=i+k-1
             * - 对于2k范围外的取值，end有两种取值情况（取决于超出2k范围的部分remind的长度）
             *      - remind >= k , end = i+k-1 剩余子串长度超出k
             *      - remind < k , end = len-1  剩余子串长度不足K
             * - 上述讨论均覆盖了k=0的情况，则可进一步整合这几种情况：
             * - 起点相同，步长均为2K，终点则是选择 Math.min(i + k, len) - 1)
             */
            reverseArr(arr, i, Math.min(i + k, len) - 1);
        }

        // 返回结果
        return new String(arr);
    }

    // 反转指定索引范围的数组（[left,right]）
    public void reverseArr(char[] chs, int start, int end) {
        // 原地反转指定索引位置范围的数组元素，避免重复截取数组的开销（Arrays.copyOfRange(arr, start,end);）
        // for (int start = 0, end = chs.length - 1; start < end; start++, end--) {
        while (start < end) {
            // 交换元素
            char temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;
        }
    }

}
