package com.noob.algorithm.solution_archive.dmsxl.leetcode.q035;

/**
 * 🟢035 搜索插入位置
 */
public class Solution3 {

    // 二分法(左闭右开)
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length; // 开区间
        while (left < right) {
            int mid = left + (right - left) / 2; // 避免溢出（left+right）/2
            // 判断target与nums[mid]的关系
            if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target == nums[mid]) {
                return mid; // 找到目标值
            }
        }
        // 返回下一个插入索引位置
        return left;
    }

}
