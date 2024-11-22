package com.noob.algorithm.dmsxl.leetcode.q056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 056 合并区间
 */
public class Solution1 {

    // 思路：排序+遍历合并
    public int[][] merge(int[][] intervals) {

        // 1.对区间进行排序（按照左边界从小到大排序）
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 2.遍历区间一一进行合并
        List<int[]> res = new ArrayList<>(); // 定义结果集
        int curLeft = intervals[0][0], curRight = intervals[0][1]; // 初始化选择第1个区间作为合并目标
        for (int i = 1; i < intervals.length; i++) {
            /**
             * curRight 与 [left,right] 进行比较
             * curRight < left : 无重叠区间，需将当前区间加入结果集，并更新合并区间的最新状态
             * curRight >= left:
             *  - curRight < right: 合并后 需更新右边界
             *  - curRight >= right: 当前已完全覆盖，无需更新
             */
            int left = intervals[i][0], right = intervals[i][1];
            if (curRight < left) {
                res.add(new int[]{curLeft, curRight});
                curLeft = left;
                curRight = right;
            } else {
                if (curRight < right) {
                    curRight = right;
                }
            }
        }
        // 遍历完成，将当前的合并区间载入
        res.add(new int[]{curLeft, curRight});

        // 返回结果
        return res.toArray(new int[res.size()][]);

    }
}