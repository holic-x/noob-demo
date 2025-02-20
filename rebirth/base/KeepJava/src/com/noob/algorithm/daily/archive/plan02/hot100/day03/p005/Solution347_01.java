package com.noob.algorithm.daily.archive.plan02.hot100.day03.p005;

import javax.swing.*;
import java.util.*;

/**
 * 🟡 347 前K个高频元素 - https://leetcode.cn/problems/top-k-frequent-elements/description/
 */
public class Solution347_01 {

    /**
     * 思路分析：top k 问题（统计元素出现频率，构建堆进行处理（大顶堆 或 维护k个元素的小顶堆））
     */
    public int[] topKFrequent(int[] nums, int k) {
        // ① 统计元素出现频率(<val,cnt>=><元素值,出现次数>)
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        // ② 构建大顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // new int[]{val,cnt}
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] == o1[1] ? o1[0] - o2[0] : o2[1] - o1[1]; // 优先按照出现频次排序，其次按照元素从小到大排序
            }
        });
        // 初始化大顶堆
        Set<Integer> keySet = map.keySet();
        for(int val : keySet){
            pq.offer(new int[]{val, map.get(val)});
        }

        // ③ 返回前K个高频元素
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }

        // 返回结果集合
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347_01 solution = new Solution347_01();
        solution.topKFrequent(nums, 2);
    }
}
