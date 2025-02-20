package com.noob.algorithm.leetcode.common150.q003;

import java.util.HashSet;

/**
 * 03 无重复字符的最长子串
 */
public class Solution1 {
    /**
     * 滑动窗口
     */
    public int lengthOfLongestSubstring(String s) {
        // 定义参数记录最大窗口
        int max = 0;
        // 定义一个滑动窗口
        HashSet<Character> set = new HashSet<>();
        // 定义滑动窗口的开始和结束索引
        int left = 0, right = 0;
        // 遍历目标字符串，根据规则滑动窗口(确认是否要将字符加入窗口),窗口滑到字符串右侧则结束
        int n = s.length();
        while (left < n && right < n) {
            // 当前校验的字符（以右指针为参考）
            char curChar = s.charAt(right);
            // 判断字符是否已在集合中
            if (!set.contains(curChar)) {
                // 如果字符不在窗口中则说明当前字符可加入窗口，并将right指向下一个字符进行判断
                set.add(curChar);
                right++;
            } else {
                // 如果字符在窗口中，则需移除该字符，并将左指针往前（这个重复的字符会在下一次判断中加入）
                set.remove(s.charAt(left));
                left++;
            }
            // 更新滑动窗口的最大值
            max = Math.max(max, set.size());
        }
        // 返回最大的内容
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.lengthOfLongestSubstring(s));
    }
}
