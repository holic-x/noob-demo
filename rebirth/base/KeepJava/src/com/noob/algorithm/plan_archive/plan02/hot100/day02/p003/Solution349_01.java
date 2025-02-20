package com.noob.algorithm.plan_archive.plan02.hot100.day02.p003;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 349 两个数组的交集 - https://leetcode.cn/problems/intersection-of-two-arrays/description/
 */
public class Solution349_01 {
    /**
     * 思路分析：哈希法
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // 遍历nums1加入哈希表并去重
        Set<Integer> set1 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        // 遍历nums2校验set1中是否已存指定元素，纳入结果集
        Set<Integer> resSet = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                resSet.add(nums2[i]);
            }
        }
        // 返回结果集
        int size = resSet.size();
        int[] res = new int[size];
        int cur = 0;
        for (int x : resSet) {
            res[cur++] = x;
        }
        return res;
    }

}
