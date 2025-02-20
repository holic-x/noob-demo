package com.noob.algorithm.leetcode.common150.q056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 056 合并区间
 */
public class Solution1 {

    /**
     * 对集合左区间进行排序，随后依次遍历更新区间
     */
    public int[][] merge(int[][] intervals) {

        // 定义集合存储结果集
        List<int[]> res = new ArrayList<>();

        // 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 选定一个起始区间进行比较(left\right 始终指向当前最新的比较区间（与遍历的区间元素进行比较）)
        int left = intervals[0][0];
        int right = intervals[0][1];
        /**
         * 遍历区间（校验合并）: 左侧区间有序，此处则以右边界为基础进行判断
         * right : [cur[0]，cur[1]]
         * right < cur[0] 没有可合并的空间，直接将{left,right}加入结果集，并将比较的point指向cur
         * right >= cur[0] 存在合并空间，进一步判断cur[1]
         *   - right <= cur[1] , 合并后为[left,cur[1]]
         *   - right > cur[1] , 合并后为[left,right] 其作为一个新的区间进入下一个区间比较
         */
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (right < cur[0]) {
                // 无可合并区间，直接加入结果集，当前比较区间指向cur
                res.add(new int[]{left, right});
                left = cur[0];
                right = cur[1];
            } else if (right >= cur[0]) {
                if (right <= cur[1]) {
                    right = cur[1];
                }
            }
        }
        // 遍历完成，将一直滚动比较的比较区间加入结果集
        res.add(new int[]{left, right});
        // 返回结果
        return res.toArray(new int[res.size()][]);
    }
}
