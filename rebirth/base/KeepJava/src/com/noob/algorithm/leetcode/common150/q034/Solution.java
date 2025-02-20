package com.noob.algorithm.leetcode.common150.q034;

/**
 * 34 在排序数组中查找元素的第1个和最后1个位置
 */
public class Solution {

    /**
     * 检索法: 二分检索
     * 转化为查找>=x的位置和<=x（（ > x） - 1）的位置
     */

    public int[] searchRange(int[] nums, int target) {
        int startIdx = -1,endIdx = -1;
        // 先检索第1个满足的target索引
        startIdx = lowBound(nums,target);
        if (startIdx == nums.length || nums[startIdx]!=target){
            // 元素不存在
            return new int[]{-1,-1};
        }
        // 如果firstIdx存在，则继续寻找endIdx的值
        endIdx = lowBound(nums,target + 1) - 1; // 寻找到>x的位置，定位到其前一个数
        return new int[]{startIdx,endIdx};
    }


    public int lowBound(int[] nums, int target) {
        // 定义检索区间
        int left = 0, right = nums.length - 1; // 闭区间
        while (left <= right) { // 区间不为空时
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 取[mid,+1,right]区间
            } else {
                right = mid - 1; // 取[left,mid-1]区间 （target==nums[mid]的情况也包括在内）
            }
        }
        // 返回元素可插入的下个位置（如果不存在可以直接返回-1）
        return left; // return -1;
    }
}
