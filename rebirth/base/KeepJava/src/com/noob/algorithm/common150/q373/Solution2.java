package com.noob.algorithm.common150.q373;

import java.util.*;

/**
 * 373 查找和最小的K对数字
 */
public class Solution2 {

    /**
     * 暴力法：获取所有的组合，计算min
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(
                (list1,list2) -> (list1.get(0) + list1.get(1)) - (list2.get(0) + list2.get(1))
        );


        int n1 = nums1.length, n2 = nums2.length;
        int min = Integer.MAX_VALUE;
        // 存储所有的可能集合
        Map<Integer, List<List<Integer>>> map = new HashMap<>();

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                int target = nums1[i] + nums2[j];
                List<Integer> temp = new ArrayList<>();
                temp.add(nums1[i]);
                temp.add(nums2[j]);
                List<List<Integer>> curList = map.getOrDefault(target, new ArrayList<>());
                curList.add(temp);
                // 更新集合
                map.put(target, curList);
                min = Math.min(min, target); // 更新最小值
            }
        }

        // 返回min指向的集合(最小的) todo 此处题目限定是返回最小的K对数，此处只是返回一对，因此不满足
        return map.get(min);
    }

}
