package com.noob.algorithm.common150.q452;

import java.util.Arrays;

/**
 * 452 用最少的箭射击气球
 */
public class Solution1 {
    /**
     * 思路：右区间排序+右端点射击
     * 1.右区间排序（按照区间的结束位置从小到大进行排序）
     * 2.右端点射击（选定第一个射击点point为第一个区间的右端点，分别和后面的区间cur(cur[0],cur[1])进行比较
     * - 如果存在交集（即cur[0]<=point说明存在重合部分，不需要消耗箭数）
     * - 反之如果cur[0]>point 则说明不存在重合部分，需要消耗1箭，并更新下一个射击点的位置继续进行比较，以此类推
     */
    public int findMinArrowShots(int[][] points) {
        int len = points.length;
        if (len == 1) {
            return 1;
        }

        // 对数组进行排序(按照右侧区间进行排序)
        // Arrays.sort(points, (p1,p2)->{ return p1[1]<p2[1]?-1:1 ;});
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int count = 1; // 初始化射箭数量
        int point = points[0][1]; // 初始化第1个射击点
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] > point) {
                // 不存在重合区间，需消耗1箭，并更新下一个射击点
                count++;
                point = points[i][1]; // 下一个射击点区间的右端点
            }
        }

        // 返回结果
        return count;
    }
}
