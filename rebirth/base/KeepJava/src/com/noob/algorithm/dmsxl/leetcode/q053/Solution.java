
package com.noob.algorithm.dmsxl.leetcode.q053;

class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curMaxSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            curMaxSum = Math.max(curMaxSum + nums[i], nums[i]);
            max = Math.max(max, curMaxSum);
        }

        // 返回结果
        return max;
    }
}