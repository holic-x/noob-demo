package com.noob.algorithm.daily.plan03.hot100_daily.day07.p022;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 🟡 452 用最少数量的箭引爆气球 - https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/description/
 */
public class Solution452_01 {

    /**
     * 贪心思路分析：
     * 基于优先右区间排序（从小到大），右区间相同则其次选择左区间排序
     */
    public int findMinArrowShots(int[][] points) {

        // 1.数组区间排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        // 2.初始化射击位置
        int shotIdx = points[0][1]; // 选择第1个区间的右端点作为第1个射击位置
        int shotCnt = 1; // 初始化第一支箭
        for (int i = 1; i < points.length; i++) {
            // 校验当前区间是否在射击范围内
            int left = points[i][0], right = points[i][1];
            // 基于上述排序规则已经约定shotIdx会小于right，因此此处只需要检验shotIdx与left的位置
            if (shotIdx < left) {
                // 没有覆盖区间，需要确定下一个射击位置
                shotCnt++;
                shotIdx = right; // 下一个射击点位置
            }
        }
        // 返回最少射击数量
        return shotCnt;
    }
}
