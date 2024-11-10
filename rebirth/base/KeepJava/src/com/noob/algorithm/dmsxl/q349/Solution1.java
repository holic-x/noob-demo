package com.noob.algorithm.dmsxl.q349;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个数组的交集（349）
 */
class Solution1 {
    // 哈希表
    public int[] intersection(int[] nums1, int[] nums2) {
        // 如果nums1、nums2其中一个为空则公共集合为空
        if (nums1 == null || nums2 == null) {
            return null;
        }

        // 将nums1放入哈希表中
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // 遍历nums2集合，如果元素存在于set中则为公共元素
        Set<Integer> set2 = new HashSet<>(); // 对nums2去重
        for (int num : nums2) {
            set2.add(num);
        }

        // 定义结果集合
        int[] res = new int[Math.min(set1.size(), set2.size())]; // 选择较小的长度初始化结果集

        // 校验公共集合
        int cur = 0; // 定义res指针游标
        for (int num : set2) {
            if (set1.contains(num)) {
                res[cur] = num;
                cur++;
            }
        }

        // 返回结果
        return res; // 注意数组长度问题处理
    }
}