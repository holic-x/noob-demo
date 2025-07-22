package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;

/**
 * 🟢 027 移除元素 - https://leetcode.cn/problems/remove-element/description/
 */
public class Solution027_02 {
    /**
     * 思路分析：双指针思路
     * 指针处理思路：left 左指针（寻找等于val的元素）、right 右指针寻找不等于val的元素，找到则替换，对撞/相遇则退出
     */
    public int removeElement(int[] nums, int val) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] != val) {
                left++;
            }

            while (left <= right && nums[right] == val) {
                right--;
            }

            // 寻找到满足条件的位置，交换位置
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                // 交换完成
                left++;
                right--;
            }
        }

        // 返回left位置
        return left;
    }
}
