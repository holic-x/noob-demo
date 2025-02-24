package com.noob.algorithm.solution_archive.leetcode.common150.q918;

import java.util.Arrays;

/**
 * 918 环形子数组的最大和
 */
public class Solution2 {
    public int maxSubarraySumCircular(int[] nums) {
        /**
         * 拆分为两部分：
         * 1.[0，i] 的最大前缀和
         * 2.[j,n] 的固定后缀和
         */
        int len = nums.length;
        int[] leftMax = new int[len];
        leftMax[0] = nums[0];

        // 计算累加和
        int[] sum = new int[len+1];

        int pre = nums[0];
        int max = nums[0];

        for(int i=1;i<len;i++){
            // [0,n]中最大子数组和
            pre = Math.max(pre+nums[i],nums[i]);
            max = Math.max(pre,max);

            // [0,i]的最大前缀
            sum[i] = sum[i-1] + nums[i-1]; // sum[i]是累加和
            leftMax[i] = Math.max(leftMax[i-1],sum[i]);
        }

        // 从右到左枚举后缀（固定后缀，选择最大前缀）
        int rightSum = 0;
        for(int i=len-1;i>0;i--){
            rightSum += nums[i];
            // 结果从两种情况中择选
            max = Math.max(max,rightSum+leftMax[i-1]);
        }

        // 返回结果
        return max;

    }
}

