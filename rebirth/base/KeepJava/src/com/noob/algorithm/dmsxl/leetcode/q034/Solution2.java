package com.noob.algorithm.dmsxl.leetcode.q034;

/**
 * 🟡 034 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution2 {

    /**
     * 二分搜索 + 左右边界寻找
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        // ① 数组本身有序，基于二分法搜索target位置idx
        int idx = binarySearch(nums, target);
        if (idx == -1) {
            return new int[]{-1, -1}; // target不存在
        }

        // ② 如果idx存在则分别向左、向右寻找其左右边界
        int left = idx, right = idx; // left、right初始化为idx位置
        while (left - 1 >= 0 && nums[left - 1] == target) { // 从idx的前一个位置开始校验
            left--;
        }
        while (right + 1 < len && nums[right + 1] == target) {// 从idx的后一个位置开始校验
            right++;
        }
        return new int[]{left, right};

        /*
        int left = idx, right = idx; // left、right初始化为idx位置
        while (left >= 0 && nums[left] == target) {
            left--;
        }
        while (right < len && nums[right] == target) {
            right++;
        }
        return new int[]{left + 1, right - 1}; // 返回结果去掉`idx`位置的讨论
         */
    }


    // 传入有序的nums[]，获取target所在索引位置，如果不存在返回-1
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间取值
        while (left <= right) {
            int mid = (right - left) / 2 + left; // (left + right) / 2
            // 比较target和中间位置元素
            if (target < nums[mid]) {
                // target 在左侧，右侧缩边
                right = mid - 1;
            } else if (target > nums[mid]) {
                // target 在右侧，左侧缩边
                left = mid + 1;
            } else {
                return mid;
            }
        }
        // 不存在target
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        Solution2 s = new Solution2();
        s.searchRange(nums, 8);
    }

}
