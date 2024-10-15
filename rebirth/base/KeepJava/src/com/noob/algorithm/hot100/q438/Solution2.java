package com.noob.algorithm.hot100.q438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的
 * 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序
 */
public class Solution2 {

    // 通过滑动窗口进行遍历
    public List<Integer> findAnagrams(String s, String p) {
        // 定义返回结果
        List<Integer> res = new ArrayList<Integer>();

        // 先对p进行排序
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        String sortedP = new String(pArr); // String.valueOf(pArr)

        // 初始化滑动窗口（这个滑动窗口的大小是固定的，其size为p的长度）
        int left = 0, right = left + sortedP.length();
        // 当窗口滑动到最右侧时（s的最后一个元素，此时只需要判断右边界即可）循环结束
        while (right <= s.length()) {
            // 判断滑动过程中，窗口内的序列是否满足字母异位词的条件
            String subStr = s.substring(left, right);
            char[] subArr = subStr.toCharArray();
            Arrays.sort(subArr);
            String sortedSubStr = new String(subArr);
            if (sortedP.equals(sortedSubStr)) {
                res.add(left);
            }
            // 执行完成，窗口继续右移
            left++;
            right++;
        }

        // 返回结果
        return res;
    }
}
