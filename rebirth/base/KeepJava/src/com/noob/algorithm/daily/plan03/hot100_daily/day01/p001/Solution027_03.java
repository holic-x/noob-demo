package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;

/**
 * 🟢 027 移除元素 - https://leetcode.cn/problems/remove-element/description/
 */
public class Solution027_03 {
    /**
     * 思路分析：双指针思路
     * 指针处理思路：left 左指针（寻找等于val的元素）、right 右指针寻找不等于val的元素，找到则替换，对撞/相遇则退出
     */
    public int removeElement(int[] nums, int val) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                // 判断right指针
                if (nums[right] != val) {
                    // 满足交换条件
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    left++;
                    right--;
                } else {
                    right--; // 继续寻找下一个位置
                }
            } else {
                left++;
            }
        }
        // 返回新数组长度
        return left;
    }
}
