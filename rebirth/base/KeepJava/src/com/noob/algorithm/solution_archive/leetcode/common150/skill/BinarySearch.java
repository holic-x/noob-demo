package com.noob.algorithm.solution_archive.leetcode.common150.skill;

/**
 * 二分检索
 */
public class BinarySearch {


    /*
    // 闭区间
    public int binarySearch1(int[] nums, int target) {
        // 定义检索区间
        int left = 0, right = nums.length - 1; // 闭区间
        while (left <= right) { // 区间不为空时
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                // 如果目标在左侧，则右侧缩边
                right = mid - 1;
            } else if (target > nums[mid]) {
                // 如果目标在右侧，则左侧缩边
                left = mid + 1;
            } else {
                return mid;
            }
        }
        // 返回元素可插入的下个位置（如果不存在可以直接返回-1）
        return left; // return -1;
    }
     */
    public int binarySearch1(int[] nums, int target) {
        // 定义检索区间
        int left = 0, right = nums.length - 1; // 闭区间
        while (left <= right) { // 区间不为空时
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 取[mid,+1,right]区间
            } else {
                right = mid - 1; // 取[left,mid-1]区间 （target==nums[mid]的情况也包括在内）
            }
        }
        // 返回元素可插入的下个位置（如果不存在可以直接返回-1）
        return left; // return -1;
    }


    // 左闭右开区间
    public int binarySearch2(int[] nums, int target) {
        // 定义检索区间
        int left = 0, right = nums.length; // 闭区间
        while (left < right) { // 区间不为空时
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 取[mid+1,right)区间
            } else {
                right = mid; // 取[left,mid)区间 （target==nums[mid]的情况也包括在内）
            }
        }
        // 返回查找到的target所在索引
        return left; // return right;
    }


    // 左闭右开区间
    public int binarySearch3(int[] nums, int target) {
        // 定义检索区间
        int left = -1, right = nums.length; // 开区间（左开右开）
        while (left + 1 < right) { // 区间不为空时
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid; // 取(mid,right)区间
            } else {
                right = mid; // 取(left,mid)区间 （target==nums[mid]的情况也包括在内）
            }
        }
        // 返回查找到的target所在索引
        return right;
    }

}
