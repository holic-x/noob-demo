package com.noob.algorithm.common150.q027;

/**
 * 27 移除元素
 */
public class Solution1 {

    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

}
