package com.noob.algorithm.solution_archive.leetcode.common150.q080;

/**
 * 80 删除数组中重复的元素II（允许重复次数超出2的可以最多出现两次）
 */
public class Solution3 {

    /**
     * 因为给定数组有序，因此相同的元素是连续的
     */
    public int removeDuplicates(int[] nums) {
        // 数组边界校验
        if (nums.length <= 2) {
            return nums.length;
        }

        // 定义双指针实现
        int cur = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[cur - 2]) {
                nums[cur] = nums[i];
                cur++;
            }
        }

        return cur;
    }
}
