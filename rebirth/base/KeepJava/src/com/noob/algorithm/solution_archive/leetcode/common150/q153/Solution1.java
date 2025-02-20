package com.noob.algorithm.solution_archive.leetcode.common150.q153;

/**
 * 153 寻找旋转排序数组的中的最小值
 */
public class Solution1 {

    /**
     * 遍历法
     */
    public int findMin(int[] nums) {
        // 初始化最小值
        int min = nums[0];

        // 闭区间思路
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < min) {
                // 如果mid所在位置元素小于min，则其左侧可能还会存在比min更小的数，因此要更新min的值，右侧缩边
                min = nums[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 返回最小值
        return min;
    }

}
