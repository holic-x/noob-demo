package com.noob.algorithm.dmsxl.leetcode.q035;

/**
 * 🟢035 搜索插入位置
 */
public class Solution1 {

    // 二分法(闭区间)
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间
        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免溢出（left+right）/2
            // 判断target与nums[mid]的关系
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target == nums[mid]) {
                return mid; // 找到目标值
            }
        }
        // 如果目标值不存在可以返回-1进行标记，此处如果要返回插入位置则选择left
        return left;
    }

}
