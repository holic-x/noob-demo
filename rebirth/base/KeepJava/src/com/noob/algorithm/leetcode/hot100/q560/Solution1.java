package com.noob.algorithm.leetcode.hot100.q560;

/**
 * 560 和为K的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列
 */
public class Solution1 {

    /**
     * 暴力法：类似子串的概念，循环遍历数组中的每个元素，然后确定以其为起点的子数组是否满足和为K
     */
    public int subarraySum(int[] nums, int k) {
        // 定义满足和为K的子数组个数
        int res = 0;
        // 遍历数组元素
        for (int i = 0; i < nums.length; i++) { // 外层循环确定子数组起点
            // 定义临时和
            int sum = 0;
            for (int j = i; j < nums.length; j++) { // 内层循环确定子数组终点
                sum += nums[j];
                // 判断当前sum值是否满足K
                if (sum == k) {
                    res++;
                }
            }
        }
        // 返回结果
        return res;
    }
}
