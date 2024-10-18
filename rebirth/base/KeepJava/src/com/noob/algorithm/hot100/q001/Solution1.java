package com.noob.algorithm.hot100.q001;

/**
 * 001.两数之和
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        // 暴力法：遍历两次数组依次判断两数之和
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
