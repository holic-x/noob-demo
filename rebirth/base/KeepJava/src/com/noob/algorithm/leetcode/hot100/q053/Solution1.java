package com.noob.algorithm.leetcode.hot100.q053;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分
 */
public class Solution1 {

    /**
     * 暴力法；以每个元素为起点，然后检索其对应的最大连续子数组，并计算和
     */
    public int maxSubArray(int[] nums) {

        // 定义返回最大和的连续子数组的sum值
        int max = -999999999; // 设定max为最小值（因为和可能为负数，此处设定max最小否则会覆盖掉负数的情况 Integer.MIN_VALUE）

        for (int i = 0; i < nums.length; i++) { // 连续子数组：起点是i，终点为j
            int curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j]; // 依次计算累加和，然后和max进行比较
                max = Math.max(max, curSum);
            }
        }

        // 返回结果
        return max;
    }
}
