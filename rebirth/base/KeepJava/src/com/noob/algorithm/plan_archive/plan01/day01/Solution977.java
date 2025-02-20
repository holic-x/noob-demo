package com.noob.algorithm.plan_archive.plan01.day01;

/**
 * 🟢 977 有序数组的平方
 * https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序
 */
public class Solution977 {
    /**
     * 双指针法：分别定义idx（指向新数组元素存放位置）、start/end（指向原数组的头尾遍历位置）
     * nums本身有序且存在负数，因此每次比较首尾元素，选择绝对值较大的元素（构成的平方数也大），从新数组尾部开始遍历存放元素
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        // 定义新数组（存放nums[]对应的平方和）
        int[] res = new int[len];
        int idx = len - 1; // 新数组的遍历逆序存放（先放大元素，从后往前遍历）
        int start = 0, end = len - 1; // 定义原数组nums的首尾指针，用于遍历nums
        while (start <= end) {
            // 比较nums的首尾指针指向元素的绝对值大小，将较大的元素存放到指定为止
            if (Math.abs(nums[start]) > Math.abs(nums[end])) {
                res[idx] = nums[start] * nums[start]; // 存放元素
                idx--; // 指向下一个存储位置
                start++; // 指向下一个遍历位置
            } else {
                res[idx] = nums[end] * nums[end];
                idx--; // 指向下一个存储位置
                end--; // 指向下一个遍历位置
            }
        }
        // 返回构建的平方数组
        return res;
    }
}
