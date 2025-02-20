package com.noob.algorithm.solution_archive.dmsxl.leetcode.q053;

/**
 * 053 最大连续子数组和
 */
public class Solution3 {

    public int maxSubArray(int[] nums) {
        // 特例分析
        if (nums.length == 1) {
            return nums[0];
        }

        int maxVal = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // 1.先更新
            curSum += nums[i];
            // 判断当前连续和是否为负数（如果为负数则不加入下一个数）
            maxVal = Math.max(maxVal, curSum); // 更新最大值（表示将当前元素加进来）

            // 2.后判断添加了这个元素之后如果连续子数组和变成负数的话，则下一个数开始从0开始计数（从次数开始断掉）
            if (curSum <= 0) {
                curSum = 0; // 重置（重新从下一个数开始计数）
            }
        }

        // 返回结果
        return maxVal;
    }

}