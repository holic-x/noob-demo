package com.noob.algorithm.daily.archive.plan02.hot100.day02.p003;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/happy-number/submissions/598388261/
 */
public class Solution001_02 {
    /**
     * 思路分析：找出数组中满足a+b=target的数组元素对应下标
     * 暴力思路：两两校验元素，双层循环遍历
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // 不存在
        return new int[]{-1, -1};
    }
}
