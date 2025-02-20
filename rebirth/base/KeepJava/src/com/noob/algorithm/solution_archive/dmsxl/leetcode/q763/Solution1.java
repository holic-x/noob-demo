package com.noob.algorithm.solution_archive.dmsxl.leetcode.q763;

import java.util.*;

/**
 * 763 划分字母区间
 */
public class Solution1 {

    /**
     * 核心条件：同一个字母最多只能出现在一个片段中
     * 1.第1次遍历：记录字母的最远出现位置（可能的切割点）
     * 2.第2次遍历：遍历字符串，同步更新当前【已出现字母的最远出现位置maxIdx】，如果当前遍历位置i与这个maxIdx相等，则此处即为切割点
     */
    public List<Integer> partitionLabels(String s) {
        // 1.存储每个字母最远出现位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        // 2.遍历字符串，同步更新当前【已出现字母的最远出现位置maxIdx】，如果当前遍历位置i与这个maxIdx相等，则此处即为切割点
        int maxIdx = 0; // 已出现字母的最远出现位置maxIdx （此处只需要记录最远位置，因此不用额外的集合空间记录已出现的字符）
        int preCutIdx = -1; // 定义上一个切割索引位置（用于计算切割段长度）
        List<Integer> res = new ArrayList<>(); // 定义切割点结果集合
        for (int i = 0; i < s.length(); i++) {
            // 更新maxIdx、preCutIdx
            maxIdx = Math.max(maxIdx, map.get(s.charAt(i)));
            // 判断maxIdx是否与当前i相等，如果相等则记录切割位置
            if (i == maxIdx) {
                // res.add(i); // 此处i为切割位置，实际存储应为切割长度
                res.add(i - preCutIdx);
                preCutIdx = i; // 记录切割位置
            }
        }

        // 返回结果
        return res;
    }
}
