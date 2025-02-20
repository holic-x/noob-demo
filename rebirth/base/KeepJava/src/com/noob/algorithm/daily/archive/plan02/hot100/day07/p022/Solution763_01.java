package com.noob.algorithm.daily.archive.plan02.hot100.day07.p022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡 763 划分字母区间 - https://leetcode.cn/problems/partition-labels/description/
 */
public class Solution763_01 {
    /**
     * 思路分析：切割位置（已出现的字母的最远出现位置）
     */
    public List<Integer> partitionLabels(String s) {
        // ① 遍历s字符串序列，获取每个字母的最远出现位置
        Map<Character, Integer> map = new HashMap<>();
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            map.put(s.charAt(i), i); // 不存在则新增，存在则覆盖最远出现位置
        }

        List<Integer> res = new ArrayList<>();

        // ② 遍历字符串序列，校验切割位置
        int preCutIdx = -1; // 上一个切割位置
        int maxIdx = -1; // 已经出现的字母的最远出现位置
        for (int i = 0; i < chs.length; i++) {
            maxIdx = Math.max(maxIdx, map.get(chs[i])); // 更新当前字符的最远出现位置
            // 如果当前位置为该字母的最远出现位置，则进行切割
            if (i == maxIdx) {
                // 进行切割，获取切割片段，并更新切割位置
                int len = i - preCutIdx;
                res.add(len);
                preCutIdx = i;
            }
        }
        // 返回结果
        return res;
    }
}
