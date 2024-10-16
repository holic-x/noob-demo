package com.noob.algorithm.hot100.q056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间
 */
public class Solution1 {

    public int[][] merge(int[][] intervals) {
        // 定义列表存储结果
        List<int[]> list = new ArrayList<int[]>();
        /**
         * 思路：
         * 1.对intervals每个数组的首位元素进行排序，然后根据右边界依次比较区间是否需要合并
         * 定义left right指针作为当前比较的左右区间
         * - 如果区间需要合并（更新当前的左右区间）
         * - 如果区间不需要合并（直接加入这个区间）
         */
        // 1.对intervals每个数组的首位元素进行排序，确定左边界
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 2.选定一个起始段
        int left = intervals[0][0];
        int right = intervals[0][1];

        // 3.依次判断区间合并(如果区间重叠组需要进行合并，并更新)
        for (int i = 1; i < intervals.length; i++) {
            /**
             * 边界分析：
             * 1.因为本身根据每个区间的左区间进行排序了，所以此处只需要关注右区间的判断
             * 2.遍历过程中如果涉及到区间合并则更新区间指针，如果不涉及合并要先将不能合并的区间先加入结果集，遍历结束后再将最终的指针区间加入结果集（避免重复添加）
             *
             * 如果right>=curLeft  指针区间 覆盖了curLeft ，说明可合并（则进一步确定合并范围）
             *  - 继续判断右边界
             *    如果right>curRight 无操作（当前指针区间可以覆盖cur）
             *    如果right<curRight 需进行合并操作，right更新为curRight
             *
             * 如果right<curLeft  （如果可以保证curLeft始终小于curRight 则不需要后面的判断）
             * 说明当前指针区间和cur区间无重复范围，直接将cur加入结果集，并将left、right指向cur
             *
             * 遍历结束，将最终的指针区间加入结果集（这个区间是一个大的合并区间）
             */
            int curLeft = intervals[i][0];
            int curRight = intervals[i][1];

            if (right >= curLeft) {
                if (right > curRight) {
                     // list.add(new int[]{left, right});
                }
                if (right < curRight) {
                    right = curRight;
                    // list.add(new int[]{left, right});
                }
            } else if (right < curLeft) {
                list.add(new int[]{left, right});
                // 更新比较的区间
                left = curLeft;
                right = curRight;
            }
        }

        list.add(new int[]{left, right});

        // 返回结果
        return list.toArray(new int[list.size()][]);
    }
}
