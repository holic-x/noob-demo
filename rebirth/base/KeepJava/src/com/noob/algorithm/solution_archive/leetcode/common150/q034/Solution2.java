package com.noob.algorithm.solution_archive.leetcode.common150.q034;

/**
 * 34 在排序数组中查找元素的第1个和最后1个位置
 */
public class Solution2 {

    /**
     * 检索法: 数组本身有序，循环遍历数组查找第1个满足条件的数据，然后进行计数
     * firstIdx，firstIdx + count
     */

    public int[] searchRange(int[] nums, int target) {
        int firstIdx = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (count == 0) {
                    firstIdx = i; // 记录第一个出现的位置
                }
                count++;
            }
        }

        if (count != 0) {
            return new int[]{firstIdx, firstIdx + count - 1}; // 存在目标元素
        } else {
            return new int[]{-1, -1};
        }
    }
}
