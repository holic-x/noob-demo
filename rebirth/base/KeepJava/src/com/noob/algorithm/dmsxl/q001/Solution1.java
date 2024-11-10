package com.noob.algorithm.dmsxl.q001;

public class Solution1 {

    // 暴力法
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // 没有满足两数之和为target的场景
        return new int[]{0, 0};
    }

}
