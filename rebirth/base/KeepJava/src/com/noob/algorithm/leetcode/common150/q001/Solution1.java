package com.noob.algorithm.leetcode.common150.q001;

/**
 * 001 两数之和
 */
public class Solution1 {
    // 暴力法
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }
}
