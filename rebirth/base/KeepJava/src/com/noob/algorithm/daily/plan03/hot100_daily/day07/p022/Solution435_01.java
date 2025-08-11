package com.noob.algorithm.daily.plan03.hot100_daily.day07.p022;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 🟡 435 无重叠区间 - https://leetcode.cn/problems/non-overlapping-intervals/description/
 */
public class Solution435_01 {

    /**
     * 思路转化：转化为用最少的箭覆盖所有区间的处理思路 =》n-shotCnt
     * - 获取最大不重叠子集（即用最少的数量射击覆盖区间），那么通过n-shotCnt得到的就是所需移除区间的最小数量
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // 1.右端点排序+右端点射击
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        // 初始化射击位置
        int shotIdx = intervals[0][1];
        int shotCnt = 1;
        for (int i = 1; i < intervals.length; i++) {
            // 校验区间是否被覆盖
            if (shotIdx <= intervals[i][0]) {
                // 选择下一个位置
                shotCnt++;
                shotIdx = intervals[i][1];
            }
        }

        // 返回结果
        return intervals.length - shotCnt;
    }
}
