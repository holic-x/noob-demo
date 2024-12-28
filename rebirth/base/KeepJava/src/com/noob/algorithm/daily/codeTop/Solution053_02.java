package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 053 最大子数组和
 */
public class Solution053_02 {

    // 动态规划思路
    public int maxSubArray(int[] nums) {

        int preMax = Math.max(Integer.MIN_VALUE, nums[0]);
        int max = preMax;

        for (int i = 1; i < nums.length; i++) {
            int curMax = Math.max(preMax + nums[i], nums[i]);
            max = Math.max(max, curMax);

            // 更新preMax
            preMax = curMax;
        }

        // 返回结果值
        return max;
    }
}
