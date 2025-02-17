package com.noob.algorithm.daily.archive.plan02.day07.p022;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 🟡 435 无重叠区间 - https://leetcode.cn/problems/non-overlapping-intervals/description/
 */
public class Solution435_01 {

    /**
     * 思路转化：m-cnt 概念，cnt最小射击数量
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // ① 基于右端点进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        // ② 寻找射击点,统计最少射击次数
        int shotIdx = intervals[0][1]; // 第一个区间的右端点作为射击点
        int cnt = 1; // 初始化弓箭数
        for (int i = 1; i < intervals.length; i++) {
            // 校验射击点是否覆盖区间，点接触不算覆盖
            if (shotIdx <= intervals[i][0]) {
                // 未覆盖区间，寻找新的射击点
                shotIdx = intervals[i][1];
                cnt++;
            }
            /*
            // 区间覆盖范围的校验条件控制
            if (shotIdx > intervals[i][0] && shotIdx <= intervals[i][1]) {
                continue;
            } else {
                // 未覆盖区间，寻找新的射击点
                shotIdx = intervals[i][1];
                cnt++;
            }
             */
        }

        // ③ 返回结果
        return intervals.length - cnt;
    }
}
