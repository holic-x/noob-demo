package com.noob.algorithm.dmsxl.leetcode.q452;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452 用最少数量的箭引爆气球
 */
public class Solution1 {

    // 思路：右区间排序 + 右端点射击
    public int findMinArrowShots(int[][] points) {
        // 根据右区间排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 遍历区间
        int shotIdx = points[0][1]; // 初始化第一个射击点
        int cnt = 1; // 定义射击次数
        int m = points.length;
        for (int i = 0; i < m; i++) {
            // 遍历区间，如果区间在上一个射击范围内则跳过
            if (points[i][0] <= shotIdx && shotIdx <= points[i][1]) {
                continue;
            }
            // 如果当前区间没有在覆盖范围内，则选择其右边界作为射击点
            shotIdx = points[i][1];
            cnt++;
        }

        // 返回结果
        return cnt;
    }
}
