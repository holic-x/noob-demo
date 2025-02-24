package com.noob.algorithm.solution_archive.leetcode.common150.q057;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 057 插入区间
 */
public class Solution1 {

    /**
     * 插入区间：intervals按照升序排列，插入一个新区间newInterval，确保插入后区间不重叠，需合并区间
     * 1.插入新区间，构建一个新的集合
     * 2.对新集合进行合并操作
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        // 获取区间个数
        int n = intervals.length;
        // 边界条件判断
        if(n==0){
            return new int[][]{{newInterval[0],newInterval[1]}};
        }

        // 1.构建新数组，将新区间插入
        int[][] tempIntervals = new int[n + 1][intervals[0].length + 1];
        System.arraycopy(intervals, 0, tempIntervals, 0, n);
        tempIntervals[intervals.length] = newInterval;

        // 2.对新的集合进行合并区间操作
        /**
         * 合并区间：
         * 排序：根据left左边界进行排序
         * 合并：初始化point（left,right），左边界有序，根据右边界确认是否要进行合并
         * right 与 [cur[0],cur[1]]进行比较
         * right < cur[0] 无公共部分，直接将cur加入结果集，随后point指向cur（更新指针区间）
         * right >= cur[0] 存在公共部分，进一步确定合并缺件
         * - right <= cur[1]: cur覆盖范围大，则需更新right，构建新point
         * - right > cur[1]:point覆盖范围大，无需操作，继续下一个区间判断
         */
        // 左区间排序
        Arrays.sort(tempIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        // 定义指针区间point 指向当前校验的区间
        int left = tempIntervals[0][0];
        int right = tempIntervals[0][1];
        // 遍历区间元素，校验合并
        for (int i = 1; i < tempIntervals.length; i++) {
            int[] cur = tempIntervals[i];
            if (right < cur[0]) {
                res.add(new int[]{left,right});
                left = cur[0];
                right = cur[1];
            } else {
                if (right <= cur[1]) {
                    right = cur[1];
                }
            }
        }
        // 遍历完成需将point加入结果集
        res.add(new int[]{left, right});

        // 返回结果集
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newInterval = new int[]{2, 5};
        Solution1 solution1 = new Solution1();
        solution1.insert(intervals, newInterval);
    }

}
