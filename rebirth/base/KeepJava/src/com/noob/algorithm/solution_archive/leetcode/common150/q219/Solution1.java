package com.noob.algorithm.solution_archive.leetcode.common150.q219;

/**
 * 219 给你一个整数数组 `nums` 和一个整数 `k` ，判断数组中是否存在两个 不同的索引 `i` 和 `j` ，
 * 满足 `nums[i] == nums[j]` 且 `abs(i - j) <= k` 。如果存在，返回 `true` ；否则，返回 `false`
 */
public class Solution1 {
    // 暴力法：双层循环嵌套，判断是否满足条件
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                // 判断是否符合条件
                if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        // 没有找到，返回false
        return false;
    }
}
