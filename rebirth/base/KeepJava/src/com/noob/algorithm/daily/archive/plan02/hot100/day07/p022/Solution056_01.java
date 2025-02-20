package com.noob.algorithm.daily.archive.plan02.hot100.day07.p022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 🟡 056 合并区间 - https://leetcode.cn/problems/merge-intervals/description/
 */
public class Solution056_01 {
    /**
     * 思路：合并重叠区间
     */
    public int[][] merge(int[][] intervals) {
        // 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 定义当前处理的合并区间
        int curLeft = intervals[0][0];
        int curRight = intervals[0][1];

        List<int[]> list = new ArrayList<>();

        // 遍历剩余区间，一一进行合并
        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 校验curRight与[left,right]的关系
            if (curRight < left) {
                // 区间不重叠，需载入区间结果集并更新比较基准
                list.add(new int[]{curLeft, curRight});
                curLeft = left;
                curRight = right;
            } else if (curRight >= left) {
                // 进一步校验right边界
                if (curRight <= right) {
                    // 区间重叠，需选择较大的右边界进行覆盖
                    curRight = right;
                } else {
                    // 当前curRight足以覆盖right边界，无需操作
                }
            }
        }

        // 遍历结束，将最后一个处理好的区间载入结果集
        list.add(new int[]{curLeft, curRight});

        // 返回结果
        return list.toArray(new int[list.size()][]);
    }
}
