package com.noob.algorithm.daily.archive.plan02.hot100.day07.p022;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🟡 452 用最少数量的箭引爆气球 - https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/description/
 */
public class Solution452_01 {

    /**
     * 贪心思路分析：将区间按照区间的右端点进行排序，然后每次选择一个右端点作为可能的射击点
     */
    public int findMinArrowShots(int[][] points) {
        int m = points.length, n = points[0].length;
        // 基于优先队列对数据进行排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] != o2[1]) ? o1[1] - o2[1] : o1[0] - o2[0]; // 优先按照右端点排序
            }
        });
        // 初始化优先队列
        for (int i = 0; i < m; i++) {
            pq.add(points[i]);
        }

        // 基于上述排序选择可能的射击点（区间右端点可作为射击点参考）
        int shotIdx = pq.poll()[1]; // 初始化选择第1个区间的右端点为射击点
        int cnt = 1;
        // 遍历剩余数据，校验当前射击点是否覆盖了该数据，如果没有覆盖则需要选择新的射击点
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (shotIdx >= cur[0] && shotIdx <= cur[1]) {
                // 满足覆盖位置，跳过
                continue;
            } else {
                // 选择新的射击位置
                shotIdx = cur[1];
                cnt++;
            }
        }
        // 返回结果
        return cnt;
    }
}
