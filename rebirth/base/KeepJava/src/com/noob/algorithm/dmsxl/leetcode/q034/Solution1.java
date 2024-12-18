package com.noob.algorithm.dmsxl.leetcode.q034;

/**
 * 🟡 034 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution1 {

    /**
     * 遍历搜索（O（n））
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        // 数组本身有序，因此可以正序搜索找到target，统计这个元素出现的次数即可（注意数组下标的处理）
        for (int i = 0; i < len; i++) {
            if (nums[i] == target) {
                // 从当前i位置开始搜索
                int cnt = 0;
                for (int j = i; j < len; j++) {
                    if (nums[i] == nums[j]) {
                        cnt++;
                    }
                }
                return new int[]{i, i + cnt - 1}; // j从i开始计数，因此此处索引统计要减1
            }
        }
        return new int[]{-1, -1};
    }
}
