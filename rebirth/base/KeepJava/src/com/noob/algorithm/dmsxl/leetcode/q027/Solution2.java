package com.noob.algorithm.dmsxl.leetcode.q027;

/**
 * 027 移除元素
 */
public class Solution2 {
    // 双指针
    public int removeElement(int[] nums, int val) {
        // 初始化左右指针
        int left = 0, right = nums.length - 1;
        // 指针相遇遍历结束
        while (left <= right) {
            // 判断当前的左右指针的位置
            if (nums[left] == val) {
                // 如果左侧找到了存储val的位置，则需进一步确定右侧的位置
                if (nums[right] != val) {
                    // 满足条件，进行交换
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                } else {
                    right--; // 该位置不满足，right继续往前
                }
            } else {
                // 左侧位置不满足，left继续往前
                left++;
            }
        }
        // 返回结果
        return left;
    }
}
