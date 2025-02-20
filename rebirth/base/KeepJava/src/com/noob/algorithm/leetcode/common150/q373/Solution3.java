package com.noob.algorithm.leetcode.common150.q373;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373 查找和最小的K对数字
 */
public class Solution3 {

    /**
     * 大顶堆（维护K个元素的大顶堆，遍历剩余元素置换最大值，直到最终堆中保留K个最小元素）
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 构建大顶堆
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(
                (list1, list2) -> (list2.get(0) + list2.get(1)) - (list1.get(0) + list1.get(1))
        );

        // 定义计数器
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums1[i]);
                temp.add(nums2[j]);
                if (count < k) {
                    heap.add(temp); // 初始化大顶堆
                } else {
                    // 超出K的部分需依次判断是否需要置换
                    List<Integer> cur = heap.peek();
                    if (cur.get(0) + cur.get(1) > nums1[i] + nums2[j]) {
                        heap.poll(); // 剔除堆顶元素
                        heap.add(temp); // 置换新元素
                    }
                }
                count++;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        // 和最小的K对数字（大顶堆中保留即为所求）
        while(!heap.isEmpty()){
            ans.add(heap.poll());
        }

        // 返回结果
        return ans;
    }

}
