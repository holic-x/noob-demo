package com.noob.algorithm.solution_archive.leetcode.common150.q373;

import java.util.*;

/**
 * 373 查找和最小的K对数字
 */
public class Solution2 {

    /**
     * 小顶堆
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 构建小顶堆
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(
               // (list1,list2) -> ( list1.get(0) + list1.get(1)) - (list2.get(0) + list2.get(1))
                Comparator.comparingInt(list -> (list.get(0) + list.get(1)))
        );

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums1[i]);
                temp.add(nums2[j]);
                heap.add(temp);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        // 和最小的K对数字
        for(int i=0;i<k;i++){
            ans.add(heap.poll());
        }

        // 返回结果
        return ans;
    }

}
