package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 349 两个数组的交集 - https://leetcode.cn/problems/intersection-of-two-arrays/description/
 */
public class Solution349_01 {
    /**
     * 思路分析：哈希法
     * 给定两个数组nums1\nums2 返回交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // 定义集合存储nums1元素
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        // 遍历nums2
        Set<Integer> ans = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                ans.add(num); // 将元素加入结果集
            }
        }

        // 转化为数组
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

}
