package com.noob.algorithm.solution_archive.dmsxl.leetcode.q035;

/**
 * 🟢035 搜索插入位置
 */
public class Solution0 {

    // 二分法(左闭右开)
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            /**
             * 如果cur=target说明当前遍历位置即为target位置，即返回i
             * 如果cur>target说明出现了没找到target，那么当前这个第一个大于target的位置就是target要插入的位置，所以也是返回i
             */
            if (nums[i] >= target) {
                return i;
            }
        }
        // 如果不存在大于等于target的数，则说明target最大，直接插入到数组尾部
        return nums.length;
    }

}
