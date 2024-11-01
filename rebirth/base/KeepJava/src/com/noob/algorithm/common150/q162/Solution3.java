package com.noob.algorithm.common150.q162;

/**
 * 162 寻找峰值
 */
public class Solution3 {
    /**
     * 思路：二分（O(logN)二分法的代名词）
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 中间判断
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
