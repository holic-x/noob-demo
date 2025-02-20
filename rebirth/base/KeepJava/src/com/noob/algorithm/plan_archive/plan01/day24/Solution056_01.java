package com.noob.algorithm.plan_archive.plan01.day24;

import java.util.*;

/**
 * 🟡 056 合并区间 - https://leetcode.cn/problems/merge-intervals/description/
 */
public class Solution056_01 {

    /**
     * 思路分析：根据区间左边界进行排序，随后校验右边界是否覆盖以决定是否要更新区间范围
     */
    public int[][] merge(int[][] intervals) {

        // 根据区间左边界进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 校验每个区间，确认是否更新区间
        List<int[]> res = new ArrayList<>();
        // 定义初始化区间的边界值（base：用于指向当前更新的区间（覆盖处理））
        int baseStart = intervals[0][0];
        int baseEnd = intervals[0][1];

        // 校验每个区间，更新覆盖范围
        for (int i = 1; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];

            // 校验baseEnd与当前区间范围的关系
            if (baseEnd < curStart) {
                // 类似[0,1] [2,3] 这种情况，无可覆盖区间，需将base载入结果集并更新base
                res.add(new int[]{baseStart, baseEnd});
                baseStart = curStart;
                baseEnd = curEnd;
            } else {
                // 在baseEnd>curStart的基础上校验baseEnd落在curEnd的左侧还是右侧
                if (baseEnd <= curEnd) {
                    // 类似[0,3] [1,5]这种情况，则需取curEnd才能覆盖
                    baseEnd = curEnd;
                } else {
                    // 类似[0,5] [1,3]这种情况，则当前base区间足以覆盖当前遍历区间，无需变更
                }
            }
        }

        // 将最后base加入结果集
        res.add(new int[]{baseStart, baseEnd});

        // 返回结果集
        // return (int[][]) res.toArray();
        return res.toArray(new int[res.size()][]);
    }

}
