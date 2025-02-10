package com.noob.algorithm.daily.plan02.day01;

/**
 * 🟢 704 二分查找 - https://leetcode.cn/problems/binary-search/
 */
public class Solution704_01 {
    public int search(int[] nums, int target) {
        int res = binarySearch(nums, target);
        return res;
    }

    // 二分查找（闭区间）
    private int binarySearch(int[] nums, int target) {
        int n = nums.length;
        // 定义双指针确定检索区间
        int left = 0, right = n - 1; // 闭区间
        while (left <= right) { // 闭区间控制
            int mid = left + (right - left) / 2; // 避免数值溢出问题
            // 校验target和nums[mid]的大小，确定范围缩减方向
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                // target 在左侧，右边界移动
                right = mid - 1;
            } else if (target > nums[mid]) {
                // target 在右侧，左边界移动
                left = mid + 1;
            }
        }
        // 未找到目标值
        return -1;
    }
}
