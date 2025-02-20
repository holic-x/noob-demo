package com.noob.algorithm.solution_archive.dmsxl.leetcode.q135;

import java.util.Arrays;

/**
 * 135 分发糖果
 */
public class Solution1 {
    // 错误思路：不能先对孩子的评分进行排序，而是要根据现有的评分序列选择发放的糖果 ❌
    public int candy(int[] ratings) {
        // 根据孩子的评分进行排序
        Arrays.sort(ratings);
        int level = 1; // 发放糖果等级,起始发1个糖果，随着上升序列递增
        int total = 0; // 发放糖果总量
        for (int i = 0; i < ratings.length - 1; i++) {
            total += level; // 累加糖果总量
            if (ratings[i + 1] > ratings[i]) {
                level++;
            }
        }
        // 返回糖果数量
        return total;
    }
}
