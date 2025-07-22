package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;

/**
 * 🟢 027 移除元素 - https://leetcode.cn/problems/remove-element/description/
 */
public class Solution027_01 {
    /**
     * 思路分析：双指针思路
     * 指针处理思路：
     * - pointer 指向新数组元素位置（表示当前位置可替换），遍历元素
     * - i 指向遍历元素位置（寻找目标元素，找到则进行覆盖）
     */
    public int removeElement(int[] nums, int val) {

        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            // 判断当前遍历位置元素
            if (nums[i] != val) {
                nums[pointer++] = nums[i];
            }
        }
        return pointer;
    }
}
