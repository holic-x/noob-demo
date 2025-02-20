package com.noob.algorithm.leetcode.common150.q149;

/**
 * 149 直线上最多的点数
 */
public class Solution1 {

    /**
     * 遍历法：
     * 1.每个点都有可能作为起点，固定起点然后遍历其他点
     * 2.起点相同，如果遍历的过程中计算两个点的斜率相同则说明在同一直线上
     * 斜率 K = (y2-y1)/(x2-x1)
     */
    public int maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        // 临界长度判断
        if (m <= 2) {
            return m;
        }

        // 大于3个节点则进行遍历判断
        int max = 2; // 最少有两个节点构成一线
        for (int i = 0; i < m - 2; i++) {
            // 获取起点坐标
            int x = points[i][0], y = points[i][1];
            // 遍历其他点
            for (int j = i + 1; j < m - 1; j++) {
                // 获取当前坐标点
                int x1 = points[j][0], y1 = points[j][1];
                // 定义当前轮次的直线点
                int cur = 2; // 起点计数为2（已经有2个点构成一线了，此处判断是否要补充新的点）
                for (int k = j + 1; k < m; k++) {
                    int x2 = points[k][0], y2 = points[k][1];
                    // 判断斜率是否相同（此处为了避免除法导致浮点数精度误差，采用惩罚思路比较）
                    if ((y1 - y) * (x2 - x1) == (y2 - y1) * (x1 - x)) {
                        cur++;
                    }
                }
                // 当前轮次判断完成，更新最大值
                max = Math.max(max, cur);
            }
        }
        // 返回结果
        return max;
    }

}
