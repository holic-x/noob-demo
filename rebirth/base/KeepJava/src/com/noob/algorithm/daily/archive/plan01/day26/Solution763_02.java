package com.noob.algorithm.daily.archive.plan01.day26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡763 划分字母区间 - https://leetcode.cn/problems/partition-labels/
 */
public class Solution763_02 {

    /**
     * 思路分析：关注【每个字母最多出现在一个片段中】这个切割条件
     * - ① 可能的切割位置：每个字母的最后一次出现的位置
     * - ② 实际的切割位置：已出现的字母序列中的最后一次出现的位置的最大值，当遍历到这个位置可以进行切割（可以确保字母只在当前片段中出现）
     */
    public List<Integer> partitionLabels(String s) {
        // 第1次遍历：统计每个字母字符的最后一次的出现位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        List<Integer> res = new ArrayList<>();

        // 第2次遍历：寻找实际的切割位置
        int cutIdx = 0; // 存储已出现的字母序列中最后一次出现的位置的最大值（切割位置会随着遍历元素的出现不断更新）
        int startIdx = 0; // 记录当前切割的起始点，用于划分切割区域（每次切割后进行更新）
        for (int i = 0; i < s.length(); i++) {
            // 更新已出现的字母的出现位置的最大值
            cutIdx = Math.max(cutIdx, map.get(s.charAt(i)));
            if (i == cutIdx) {
                // 如果遍历到当前的最大切割位置，则进行切割操作
                res.add(i - startIdx + 1);
                startIdx = i + 1; // 选择切割，更新下一个切割范围的起始点
            }
        }

        // 返回结果
        return res;
    }
}
