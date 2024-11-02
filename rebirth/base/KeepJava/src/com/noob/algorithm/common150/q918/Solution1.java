package com.noob.algorithm.common150.q918;

import java.util.Arrays;

/**
 * 918 环形子数组的最大和
 * todo
 */
public class Solution1 {
    public int maxSubarraySumCircular(int[] nums) {
        // 扩展数组 + 滑动窗口思路
        int len = nums.length;
        int[] ext = new int[len * 2];
        Arrays.copyOfRange(nums,0,len-1);
        Arrays.copyOfRange(nums,len,ext.length-1);

        // 通过滑动窗口固定子数组
        int start = 0,end = len-1;
        // 初始化窗口的值
        int curSum = Integer.MIN_VALUE;
        for(int i=0;i<len;i++){
            curSum += nums[i];
        }

        int max = nums[0];
        while(end<ext.length){
            // 左侧元素移出，加入右侧元素
            curSum = curSum - nums[start] + nums[end+1];

            // 更新最大值
            max = Math.max(max,curSum);

            // 移动指针
            start++;
            end++;

        }

        // 返回结果
        return max;
    }
}

