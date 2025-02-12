package com.noob.algorithm.daily.plan02.day03.p005;

import java.util.*;

/**
 * 🟡 347 前K个高频元素 - https://leetcode.cn/problems/top-k-frequent-elements/description/
 */
public class Solution347_02 {

    /**
     * 思路分析：top k 问题（统计元素出现频率，构建堆进行处理（大顶堆 或 维护k个元素的小顶堆））
     */
    public int[] topKFrequent(int[] nums, int k) {
        // ① 统计元素出现频率(<val,cnt>=><元素值,出现次数>)
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        // ② 构建小顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // new int[]{val,cnt}
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] == o1[1] ? o1[0] - o2[0] : o1[1] - o2[1]; // 优先按照出现频次排序，其次按照元素从小到大排序
            }
        });
        // 维护K个元素的小顶堆
        Set<Integer> keySet = map.keySet();
        int cur = 0;
        for (int val : keySet) {
            if (cur < k) {
                // 初始化维护K个元素
                pq.offer(new int[]{val, map.get(val)});
                cur++;
            } else {
                // 遍历剩余元素（将其与堆顶元素进行比较，依次将堆中的最小值置换出来）
                int curTopVal = pq.peek()[0];
                int curTopCnt = pq.peek()[1];
                if (curTopCnt < map.get(val)) {
                    // 置换较小出现频次的元素
                    pq.poll();
                    pq.offer(new int[]{val, map.get(val)});
                    cur++;
                }
            }
        }

        // ③ 当前堆中剩余的即为前K大出现频次元素
        int[] res = new int[k];
        int p = 0;
        while (!pq.isEmpty()) {
            res[p++] = pq.poll()[0];
        }

        // 返回结果集合
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347_02 solution = new Solution347_02();
        solution.topKFrequent(nums, 2);
    }
}
