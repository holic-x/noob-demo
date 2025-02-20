package com.noob.algorithm.solution_archive.dmsxl.leetcode.q034;

/**
 * 🟡 034 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution3 {

    /**
     * 直接使用二分搜索定位左右边界，然后校验左右边界的索引关系
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        // ① 分别调用二分检索获取到左右边界
        int leftBorder = binarySearchLeftBorder(nums, target);
        int rightBorder = binarySearchRightBorder(nums, target);

        // ② 校验左右边界索引关系：如果左右边界指向索引有一个不存在，则说明target不存在
        if (leftBorder == -2 || rightBorder == -2) { // 此处设定为-2
            return new int[]{-1, -1};
        }
        if (rightBorder - leftBorder > 1) {
            return new int[]{leftBorder + 1, rightBorder - 1};
        }
        return new int[]{-1, -1};
    }

    // 传入有序的nums[]，获取target所在索引位置，如果不存在返回-1
    public int binarySearchLeftBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间取值
        int leftBorder = -2;
        while (left <= right) {
            int mid = (right - left) / 2 + left; // (left + right) / 2
            // 比较target和中间位置元素
            if (target <= nums[mid]) {
                right = mid - 1; // target 在左侧，右侧缩边
                leftBorder = right; // 寻找左边界
            } else if (target > nums[mid]) {
                // target 在右侧，左侧缩边
                left = mid + 1;
            }
        }
        return leftBorder;
    }

    // 传入有序的nums[]，获取target所在索引位置，如果不存在返回-1
    public int binarySearchRightBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间取值
        int rightBorder = -2;
        while (left <= right) {
            int mid = (right - left) / 2 + left; // (left + right) / 2
            // 比较target和中间位置元素
            if (target < nums[mid]) {
                right = mid - 1;// target 在左侧，右侧缩边
            } else if (target >= nums[mid]) {
                left = mid + 1; // target 在右侧，左侧缩边
                rightBorder = left; // 寻找右边界
            }
        }
        return rightBorder;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        Solution3 s = new Solution3();
        s.searchRange(nums, 8);
    }

}
