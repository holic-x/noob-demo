package com.noob.algorithm.solution_archive.leetcode.common150.q028;

/**
 * 028 找出字符串中第一个匹配项的下标
 */
public class Solution1 {

    /**
     * 思路：快速判断target字符串是否为原字符串的一部分，如果不是则返回-1，如果是则找出其索引
     * 滑动窗口思路：定义一个和target等大的窗口，因此滑动校验目标字符串是否存在target
     */
    public int strStr(String haystack, String needle) {
        // 定义一个滑动窗口的开始和结束索引的起始位置
        int start = 0;
        int end = needle.length();

        // 遍历字符串，确认target是否存在（当end滑到右侧表示遍历结束）
        while(end<=haystack.length()) { // 注意边界条件
            // 判断窗口是否和target匹配，如果匹配则返回
            if(haystack.substring(start,end).equals(needle)) {
                return start;
            }
            // 如果没有找到合适的值，则窗口继续向右移动
            start ++;
            end ++;
        }
        // 无响应结果
        return -1;
    }
}
