package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.HashSet;

/**
 * 🟢 349 两个数组的交集 - https://leetcode.cn/problems/intersection-of-two-arrays/description/
 * 概要：给定两个数组nums1\nums2 返回交集
 */
public class Solution349_01 {
    /**
     * 思路分析：
     * 给定两个数组元素，返回交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        // 哈希表处理思路
        HashSet<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // 遍历nums2, 校验公共集合
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                set.add(num); // 存在公共数
            }
        }

        // 返回结果
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

}
