package com.noob.algorithm.solution_archive.leetcode.common150.q034;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 34 在排序数组中查找元素的第1个和最后1个位置
 */
public class Solution1 {

    // 二分查找法：正向一次二分查找左边界、反向一次二分查找右边界
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums,target);

        // 反转数组
        int[] reverseNums = new int[nums.length];
        int idx = 0;
        for(int i=nums.length-1;i>=0;i--){
            reverseNums[idx++] = nums[i];
        }

        // 逆序二分查找检索右边界
        int rightIdx = binarySearch(reverseNums,target);

        if(leftIdx != -1 && rightIdx != -1){
            return new int[]{leftIdx-1, nums.length-rightIdx};
        }else{
            return new int[]{-1, -1};
        }
    }

    // 二分法检索
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断target是否匹配
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 结果不存在
        return -1; // return left 表示下一个可插入的位置
    }
}
