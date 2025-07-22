package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;

/**
 * 🟢 704 二分查找 - https://leetcode.cn/problems/binary-search/
 */
public class Solution704_01 {

    /**
     * 思路分析：数组本身有序，直接进行二分检索（3种不同的检索处理）
     */
    public int search(int[] nums, int target) {
        // return 0;
        return binarySearch01(nums, target);
        // return binarySearch02(nums, target);
        // return binarySearch03(nums, target);
    }

    // 闭区间：[left,right]
    private int binarySearch01(int[] nums, int target) {
        // 初始化指针
        int left = 0, right = nums.length - 1;
        // 遍历元素，处理指针
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    // 左闭右开区间：[left,right)
    private int binarySearch02(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return -1;
    }

    // 闭区间：(left,right)
    private int binarySearch03(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return -1;
    }

}
