package com.noob.algorithm.plan_archive.plan01.day04;

/**
 * 🟢 001 两数之和
 */
public class Solution001_01 {
    /**
     * 暴力法：双层嵌套循环
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j}; // 返回满足调价你的数组下标
                }
            }
        }
        // 未找到满足条件的元素组合
        return new int[]{};
    }
}
