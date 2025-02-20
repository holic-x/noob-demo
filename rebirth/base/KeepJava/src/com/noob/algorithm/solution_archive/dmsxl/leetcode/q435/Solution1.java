package com.noob.algorithm.solution_archive.dmsxl.leetcode.q435;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435 无重叠区间
 */
public class Solution1 {

    public int eraseOverlapIntervals(int[][] intervals) {

        // 1.根据右区间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 2.遍历区间
        int rightIdx = intervals[0][1]; // 初始化第一个右端点
        int cnt = 1; // 记录可保留的区间个数（可以理解为射箭数）
        int m = intervals.length;

        for (int i = 0; i < m; i++) {
            // 遍历区间，如果区间不上一个右端点的覆盖范围内（单点接触视为不重叠）则需进行标记（射出一箭）
            if (rightIdx <= intervals[i][0]) { // 如果上一个右边界位置位于当前比较区间边界的情况视作不覆盖
                cnt++;
                rightIdx = intervals[i][1];
            }
        }

        // 返回结果（需要删除的区间的最小数量为总区间数量减去需要保留的区间数量）
        return m - cnt;
    }
}
