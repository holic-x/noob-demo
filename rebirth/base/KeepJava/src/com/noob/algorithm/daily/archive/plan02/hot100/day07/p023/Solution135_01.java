package com.noob.algorithm.daily.archive.plan02.hot100.day07.p023;

/**
 * 🔴 135 分发糖果 - https://leetcode.cn/problems/candy/description/
 */
public class Solution135_01 {

    /**
     * 思路分析：需满足每个孩子至少分配到1个糖果，且相邻两个孩子评分更高的孩子获得更多的糖果
     * 此处涉及到两个因素，每个孩子分配到的糖果收到左右两边孩子分配糖果的影响，因此此处需要控制变量，分别讨论左右两边的情况影响
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        // 基于左边孩子的影响，定义满足条件的情况下每个孩子分得的最少糖果数量
        int[] left = new int[n];
        left[0] = 1; // 每个孩子至少分配到1个糖果
        // 从左到右遍历
        for (int i = 1; i < n; i++) {
            // 如果当前小朋友评分更高则可以获取更多的糖果，至少比其左侧的小朋友多1个
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                // 其余情况下只给他发1个就能在最少糖果的限制下还能满足要求
                left[i] = 1;
            }
        }

        // 同理，基于右侧孩子的影响，定义满足条件的情况下每个孩子分得的最少糖果数量
        int[] right = new int[n];
        right[n - 1] = 1; // 每个孩子至少分配到1个糖果
        for (int i = n - 2; i >= 0; i--) {
            // 如果当前小朋友比起右侧小朋友的评分高则多派1个糖果，否则只发1个
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // 同时遍历两个数组，校验同时满足两个条件的情况下的最少糖果数
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += Math.max(left[i], right[i]); // 同时满足两个条件，取max
        }

        // 返回结果
        return cnt;
    }
}
