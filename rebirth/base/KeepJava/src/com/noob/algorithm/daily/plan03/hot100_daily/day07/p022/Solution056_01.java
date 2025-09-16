package com.noob.algorithm.daily.plan03.hot100_daily.day07.p022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 🟡 056 合并区间 - https://leetcode.cn/problems/merge-intervals/description/
 */
public class Solution056_01 {
    /**
     * 思路分析：区间排序（优先左区间从小到大排序，其次右区间从小到大）
     */
    public int[][] merge(int[][] intervals) {
        // 1.区间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        // 2.区间合并处理

        List<int[]> ans = new ArrayList<>();

        // 定义当前处理的合并区间
        int curLeft = intervals[0][0];
        int curRight = intervals[0][1];

        // 遍历区间处理合并（校验区间有没有重叠部分，存在重叠则进行合并）
        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (curRight < left) {
                // 区间无重叠部分，载入结果集
                ans.add(new int[]{curLeft, curRight});
                // 更新下一个待合并区间
                curLeft = left;
                curRight = right;
            } else {
                // 区间存在重叠，处理合并后的结果
                if (curRight <= right) {
                    curRight = right; // 需处理合并
                } else {
                    // 已经包括在合并范围内，无需处理
                }
            }
        }

        // 将最后的合并区间载入
        ans.add(new int[]{curLeft, curRight});

        return ans.toArray(new int[ans.size()][]);
    }
}
