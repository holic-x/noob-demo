package com.noob.algorithm.daily.plan02.day01.p001;

import java.util.Arrays;

/**
 * 🟢 977 有序数组的平方 - https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 */
public class Solution977_01 {

    /**
     * 思路分析：数组本身按照非递减顺序排序，返回每个数字的平方组成的新数组
     * 暴力思路：构建数组计算平方和，直接排序
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // 遍历元素，计算平方和
        for (int i = 0; i < n; i++) {
            res[i] = nums[i] * nums[i];
        }
        // 排序
        Arrays.sort(res);
        // 返回排序结果
        return res;
    }
}
