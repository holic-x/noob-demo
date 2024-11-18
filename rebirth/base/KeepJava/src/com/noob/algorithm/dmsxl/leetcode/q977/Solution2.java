package com.noob.algorithm.dmsxl.leetcode.q977;

import java.util.Arrays;

/**
 * 977 有序数组的平方
 */
public class Solution2 {

    // 计算平方 + 直接排序
    public int[] sortedSquares(int[] nums) {
        // 1.计算平方
        for(int i=0;i<nums.length;i++){
             nums[i] *= nums[i];
        }
        // 2.直接排序
        Arrays.sort(nums);
        return nums;
    }

}
