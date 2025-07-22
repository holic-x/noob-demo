package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;

/**
 * 🟢 977 有序数组的平方 - https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 */
public class Solution977_01 {

    /**
     * 思路分析：有序数组的平方，遍历顺序从左到右、元素填充顺序从右到左
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 定义结果集
        int[] ans = new int[nums.length];
        int idx = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                ans[idx--] = nums[left] * nums[left];
                left++;
            } else {
                ans[idx--] = nums[right] * nums[right];
                right--;
            }
        }
        return ans;
    }
}
