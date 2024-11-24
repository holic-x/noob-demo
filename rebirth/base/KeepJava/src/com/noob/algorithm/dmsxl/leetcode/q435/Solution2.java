package com.noob.algorithm.dmsxl.leetcode.q435;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435 无重叠区间
 */
public class Solution2 {

    // todo 错误
    public int eraseOverlapIntervals(int[][] intervals) {

        // 1.根据右区间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]){
                    return o1[0]-o2[0];
                }else{
                    return o1[1] - o2[1];
                }
            }
        });

        // 2.遍历区间
        int rightIdx = intervals[0][1]; // 初始化第一个右端点
        int m = intervals.length;
        // 定义重复区间个数统计(要移除的区间)
        int removeCnt = 0;

        for (int i = 0; i < m; i++) {
            // 遍历区间，如果区间上一个右端点的覆盖范围内（单点接触视为不重叠）则视为区间重叠，需要记录重叠区间个数
            if (intervals[i][0] < rightIdx && rightIdx < intervals[i][1]) {
                removeCnt++;
            }
            // 如果当前区间没有在覆盖范围内，则更新下一个判断的右端点（不存在交集、或者存在交集且当前区间的end小于前一个区间的end）
            else if (intervals[i][1] < rightIdx || intervals[i][0] >= rightIdx) {
                rightIdx = intervals[i][1];
            }
        }

        // 返回结果（重复区间的个数即为需要删除的区间的最小数量）
        return removeCnt;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        Solution2 solution2 = new Solution2();
        solution2.eraseOverlapIntervals(nums);
    }



}
