package com.noob.algorithm.plan_archive.plan01.day26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 🟡 056 合并区间 - https://leetcode.cn/problems/merge-intervals/description/
 */
public class Solution056_01 {

    /**
     * 思路分析：根据区间左边界进行排序，随后选择base区间，校验每个区间与base的覆盖关系，确认是否要更新base
     */
    public int[][] merge(int[][] intervals) {

        // 根据区间的左边界进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
//                return o1[0]-o2[0];
            }
        });

        List<int[]> list = new ArrayList<>();

        // 初始化base区间左右边界
        int baseStart = intervals[0][0];
        int baseEnd = intervals[0][1];
        // 遍历其他区间，确认覆盖关系（处理合并）
        for (int i = 1; i < intervals.length; i++) {
            // 左边界大小已排序好，此处只需要关注右边界的覆盖范围
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            if (baseEnd < curStart) {
                // 当前base区间与当前遍历区间无重叠区域，需更新base（例如[1,3]  [4,5]）
                list.add(new int[]{baseStart, baseEnd});
                baseStart = curStart;
                baseEnd = curEnd;
            } else {
                // 校验baseEnd与当前遍历区间右端点的位置
                if (baseEnd <= curEnd) {
                    // 需更新base区间以进行合并（例如[1,3] [2,5]）
                    baseEnd = curEnd;
                } else {
                    // 无需更新操作（例如[1,3] [2,3]）
                    continue;
                }
            }
        }

        // 所有区间遍历结束，需将最后一个合并的区间结果载入结果集
        list.add(new int[]{baseStart, baseEnd});

        // 返回结果集
        return list.toArray(new int[list.size()][]);
    }

}
