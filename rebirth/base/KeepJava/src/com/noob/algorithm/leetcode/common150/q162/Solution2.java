package com.noob.algorithm.leetcode.common150.q162;

/**
 * 162 寻找峰值
 */
public class Solution2 {
    /**
     * 思路：最大值
     */
    public int findPeakElement(int[] nums) {
        // 定义当前最大值的索引，初始化为0
        int maxIdx = 0;
        for(int i=0;i<nums.length;i++){
            // 如果当前遍历元素值大于目前的最大值元素，则进行最大值索引替换
            if(nums[i] > nums[maxIdx]){
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
