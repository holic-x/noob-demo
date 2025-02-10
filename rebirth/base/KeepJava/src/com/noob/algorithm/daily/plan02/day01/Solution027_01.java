package com.noob.algorithm.daily.plan02.day01;

/**
 * 🟢 027 移除元素 - https://leetcode.cn/problems/remove-element/description/
 */
public class Solution027_01 {
    /**
     * 思路分析：移除nums中等于val的元素，不care后面的元素排位
     */
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        // 定义双指针处理数组元素：left 用于寻找等于val的元素，right用于寻找不等于val的元素，将val交换到后面的位置
        int left = 0, right = n - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
            } else if (nums[left] == val) {
                // 需要将val交换到后面的合适的位置
                if (nums[right] == val) {
                    right--;
                } else if (nums[right] != val) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = nums[left];
                    left++;
                    right--;
                }
            }
        }
        // 返回非val的元素个数，即left位置
        return left;
    }
}
