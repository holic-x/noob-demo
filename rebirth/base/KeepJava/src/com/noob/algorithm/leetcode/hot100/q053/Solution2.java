package com.noob.algorithm.leetcode.hot100.q053;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分
 */
public class Solution2 {

    /**
     * 暴力法；以每个元素为起点，然后检索其对应的最大连续子数组，并计算和
     */
    public int maxSubArray(int[] nums) {
        // 定义结果
        int res = nums[0];

        // 定义当前最大值
        int currentMax = nums[0];

        // 单层循环
        for (int i = 1; i < nums.length; i++) {
            // 获取当前子组合的最大值（当前子组合的最大值被拆解为两部分：上一个子组合的最大值+num[i]、其自身num[i]）
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            // 更新最大值
            res = Math.max(res, currentMax);
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5,4,-1,7,8};
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.maxSubArray(nums));

    }
}
