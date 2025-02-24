package com.noob.algorithm.solution_archive.dmsxl.leetcode.q347;

import java.util.*;

/**
 * 347 前K个高频元素
 */
public class Solution2 {

    /**
     * 暴力法：统计每个元素出现的频率（次数），然后进行排序，返回前K的元素
     */
    public int[] topKFrequent(int[] nums, int k) {

        // 1.统计每个元素出现的频率（次数）
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 2.基于频率进行排序（或者借助堆来构建前K）,此处采用小顶堆实现
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            // 小顶堆存储的是数组元素，排序规则则需根据数组元素获取到频率进行比较
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2); // 根据元素对应的出现频次进行比较构建
            }
        });

        /**
         * 先录入K个元素(此处限定k>=map.size)
         * 然后遍历剩余元素，判断当前遍历元素和堆顶元素的关系：如果大于堆顶则置换，慢慢将小值置换出来，最终遍历完成后小顶堆留存的就是最大的前K
         */
        for (int key : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(key);
            } else {
                if (map.get(queue.peek()) < map.get(key)) {
                    // 置换
                    queue.poll();
                    queue.offer(key);
                }
            }
        }

        // 3.遍历小顶对堆
        int[] res = new int[k];
        int cur = 0;
        while (!queue.isEmpty()) {
            res[cur++] = queue.poll();
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution2 solution2 = new Solution2();
        solution2.topKFrequent(nums, 2);
    }
}
