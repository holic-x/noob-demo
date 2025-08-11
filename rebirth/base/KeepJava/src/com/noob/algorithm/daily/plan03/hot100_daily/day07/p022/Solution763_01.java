package com.noob.algorithm.daily.plan03.hot100_daily.day07.p022;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 763 划分字母区间 - https://leetcode.cn/problems/partition-labels/description/
 */
public class Solution763_01 {
    /**
     * 思路分析：
     * 目标切割位置：选择当前已遍历字符的最远出现位置进行切割
     */
    public List<Integer> partitionLabels(String s) {
        // 定义map存储每个字符的最远出现位置(此处可以用数组处理下标表示字符，数组元素值表示该元素的最远出现位置)
        int[] points = new int[26];
        for (int i = 0; i < s.length(); i++) {
            points[s.charAt(i) - 'a'] = i;
        }

        // 再次遍历数组，处理切割位置
        List<Integer> ans = new ArrayList<>(); // 定义结果集记录切割长度列表（非切割位置）
        int maxIdx = -1;
        int lastCutIdx = -1; // 记录上一个切割位置
        for (int i = 0; i < s.length(); i++) {
            // 获取当前已出现元素的最远出现位置
            maxIdx = Math.max(maxIdx, points[s.charAt(i) - 'a']);
            // 如果遍历元素到达当前出现元素的最远出现位置则进行切割
            if (i == maxIdx) {
                // 切割
                ans.add(maxIdx - lastCutIdx);
                // 更新下一个切割位置
                lastCutIdx = maxIdx;
            }
        }
        // 返回结果
        return ans;
    }
}
