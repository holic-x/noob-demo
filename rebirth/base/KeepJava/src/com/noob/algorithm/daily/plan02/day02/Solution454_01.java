package com.noob.algorithm.daily.plan02.day02;

import java.util.*;

/**
 * 🟡 454 四数相加II - https://leetcode.cn/problems/4sum-ii/description/
 */
public class Solution454_01 {

    /**
     * 思路分析：计算有多少个元组满足a+b+c+d=0
     * 两两处理：nums1+nums2得到map1(和及其构成元组数)、nums1+nums2得到map2(和及其构成元组数)
     * 然后基于哈希表的思路校验map1+map2=0
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 两两处理，获取数组和映射
        Map<Integer, Integer> map1 = getSumMap(nums1, nums2);
        Map<Integer, Integer> map2 = getSumMap(nums3, nums4);
        // 基于哈希思路校验map1[i]+map2[j]=0的情况并统计元组个数
        int res = 0;
        Set<Integer> keySet = map2.keySet();
        for (int key : keySet) {
            if (map1.containsKey(0 - key)) {
                res += map1.get(0 - key) * map2.get(key);
            }
        }
        // 返回结果
        return res;
    }

    // 计算两个数组元素和的出现次数
    private Map<Integer, Integer> getSumMap(int[] nums1, int[] nums2) {
        // 计算nums1与nums2可构成的和元组情况
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        return map;
    }
}
