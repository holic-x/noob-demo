package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 034 在排序数组中查找元素的第1个和最后一个位置
 */
public class Solution034_01 {

    /**
     * 思路分析：数组本身有序，查找元素可能存在多个
     * - 通过二分法检索找到目标元素位置idx，然后基于这个idx向两边发散检索最左、最右位置
     */
    public int[] searchRange(int[] nums, int target) {
        // ① 通过二分检索查找目标元素位置
        int idx = binarySearch(nums, target);
        if (idx == -1) {
            return new int[]{-1, -1}; // 未找到
        }
        // ② 目标元素存在，基于idx位置向两侧发散检索
        int leftIdx = idx;
        while (leftIdx >= 0 && nums[leftIdx] == nums[idx]) {
            leftIdx--;
        }
        int rightIdx = idx;
        while (rightIdx < nums.length && nums[rightIdx] == nums[idx]) {
            rightIdx++;
        }
        // 返回检索结果
        return new int[]{leftIdx + 1, rightIdx - 1};
    }

    // 二分检索目标元素位置
    private int binarySearch(int[] nums, int target) {
        // 定义指针
        int left = 0, right = nums.length - 1; // 闭区间[left,right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 目标元素在右边，左侧缩边
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 目标元素在左侧，右侧缩边
                right = mid - 1;
            }
        }
        // 未找到目标元素
        return -1;
    }

}
