package com.noob.algorithm.solution_archive.leetcode.hot100.q438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的
 * 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序
 */
public class Solution1 {

    /**
     * 字母异位词：排序后的序列相同
     * 暴力法：
     */
    public List<Integer> findAnagrams(String s, String p) {

        // 定义返回结果
        List<Integer> res = new ArrayList<Integer>();

        int sLen = s.length();
        int pLen = p.length();

        // 对目标字符串进行排序
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        String sortedP = String.valueOf(pArr);

        // 遍历判断以每个元素为起点，其对应子串是否满足字母异位词要求
        for (int i = 0; i < sLen - pLen + 1; i++) {
            // 排序后的序列相同
            String subStr = s.substring(i, i + pLen);
            char[] subArr = subStr.toCharArray();
            Arrays.sort(subArr);
            String sortedSub = String.valueOf(subArr);
            if (sortedP.equals(sortedSub)) {
                // 满足字母异位词要求，将索引加入结果集
                res.add(i);
            }
        }

        // 返回结果集
        return res;
    }
}
