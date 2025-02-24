package com.noob.algorithm.solution_archive.leetcode.common150.q502;

import java.util.*;

/**
 * 502 IPO
 */
public class Solution1 {

    // 贪心 + 堆
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int len = profits.length; // 项目个数
        List<int[]> pros = new ArrayList<>();
        // 将项目的启动资金和净利润组装到一起
        for (int i = 0; i < len; i++) {
            pros.add(new int[]{capital[i], profits[i]});
        }

        // 根据项目启动资金进行升序排序（capital）
        Collections.sort(pros, (a, b) -> a[0] - b[0]); // 根据list的每个数组组合元素的启动资金进行排序
        // 根据项目净利润进行排序（profits），借助优先队列(大顶堆)
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        int idx = 0;
        while (k > 0) {
            // 当项目列表存在可做的项目（idx未到数组尾部，且当前指向的项目启动资金<=w）
            while (idx < len && pros.get(idx)[0] <= w) {
                queue.offer(pros.get(idx)[1]);
                idx++;
            }
            if (queue.isEmpty()) break; // 没有可做的project
            w += queue.poll(); // 利润累加每次都拿最大利润的项目去做
            k--;
        }
        return w;
    }
}
