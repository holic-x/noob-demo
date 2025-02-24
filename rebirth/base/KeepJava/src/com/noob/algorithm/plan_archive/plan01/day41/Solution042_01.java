package com.noob.algorithm.plan_archive.plan01.day41;

/**
 * 042 接雨水 - https://leetcode.cn/problems/trapping-rain-water/description/
 */
public class Solution042_01 {

    /**
     * 思路分析：计算每个格子的蓄水量（柱子左侧、右侧取低点，然后减去自身水柱高度求的雨水面积和）
     */
    public int trap(int[] height) {
        int len = height.length;
        // 1.计算每个柱子左侧的最高水位和右侧的最高水位
        int[] left = new int[len];
        left[0] = height[0]; // 初始化
        for(int i = 1;i<len;i++){
            left[i] =Math.max(left[i-1],height[i]);
        }
        int[] right = new int[len];
        right[len-1] = height[len-1]; // 初始化
        for(int i = len-2;i>=0;i--){
            right[i] =Math.max(right[i+1],height[i]);
        }

        // 2.遍历水柱得到每格的面积，累加面积和
        int area = 0;
        for(int i=0;i<len;i++){
            area += Math.min(left[i],right[i]) - height[i];
        }

        // 返回结果
        return area;
    }

}
