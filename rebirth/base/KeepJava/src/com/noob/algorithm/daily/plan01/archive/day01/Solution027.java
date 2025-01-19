package com.noob.algorithm.daily.plan01.archive.day01;

/**
 * 🟢 027 移除元素
 * https://leetcode.cn/problems/remove-element/description/
 */
public class Solution027 {
    /**
     * 双指针思路（原地交换元素）
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int left = 0; // 左指针用于寻找等于val的值
        int right = nums.length - 1; // 右指针用于寻找不等于val的值
        // 双指针遍历，如果找到等于val的值则进行交换
        while (left <= right) {
            if (nums[left] == val) {
                // 需要进行交换，需进一步确认可交换的位置
                if (nums[right] == val) {
                    right--; // right向中间靠拢
                } else {
                    // 进行交换操作
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    // 交换完成，双指针继续向中间移动
                    left++;
                    right--;
                }
            } else {
                // 如果当前left指向元素不等于val，则继续寻找left指向的下一个可交换位置
                left++;
            }
        }
        return left;
    }
}
