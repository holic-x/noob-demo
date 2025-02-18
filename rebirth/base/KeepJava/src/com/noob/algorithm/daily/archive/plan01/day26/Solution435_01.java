package com.noob.algorithm.daily.archive.plan01.day26;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 🟡 435 无重叠区间 - https://leetcode.cn/problems/non-overlapping-intervals/description/
 */
public class Solution435_01 {

    /**
     * 思路：m-cnt概念（用区间总数减去最小射击数）
     * - 本题和【452-用最少数量的箭引爆气球】的题型思路大同小异，但需注意边界处理和返回结果
     * - 【452】中求的是最少射击箭数（可以理解为此处保留的区间个数统计），那么【435】中返回的结果应该是`m-cnt`得到移除区间的最小数量
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        // 根据区间右边界进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        int m = intervals.length;

        // 初始化选择覆盖点（射击位置）
        int shotIdx = intervals[0][1]; // 选择区间右端点作为射击位置
        int cnt = 1; // 记录射击数
        // 遍历区间确认覆盖范围
        for (int i = 0; i < m; i++) {
            // 校验射击点与区间左端点的关系（此处由于触点不计入覆盖范围，因此此处有效的区间为(left,right]）
            if (shotIdx <= intervals[i][0]) {
                // 选择新的射击点，并记录射击数量
                shotIdx = intervals[i][1];
                cnt++;
            }
        }
        // cnt 表示用最少数量的箭覆盖范围（可以理解为此处保留的区间个数统计），因此结果要去除的区间个数为m-cnt
        return m - cnt;
    }
}
