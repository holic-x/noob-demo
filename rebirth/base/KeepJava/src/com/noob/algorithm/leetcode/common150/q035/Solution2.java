package com.noob.algorithm.leetcode.common150.q035;

/**
 * 035 搜索插入位置
 */
public class Solution2 {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0, end = len - 1;
        while (start <= end) {
            // 获取中间点
            int mid = (start + end) / 2;
            // 判断target所在区间
            if (target < nums[mid]) {
                // target 在左区间，右指针缩边
                end = mid - 1;
            } else if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                // target 在右区间，左指针缩边
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        Solution2 solution1 = new Solution2();
        solution1.searchInsert(nums, 3);
    }
}
