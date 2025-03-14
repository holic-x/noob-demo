package com.noob.algorithm.daily.codeTop;

/**
 * 🟢 026 删除有序数组中的重复项 - https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
 */
public class Solution026_01 {

    // 基于原地删除思路，确保相对顺序，使得每个元素只出现一次
    public int removeDuplicates(int[] nums) {
        // 指针处理
        int cur = 0; // 指向当前原地填充位置
        // 遍历数组，如果出现连续重复则跳过，其余元素直接填充到cur指定的位置
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 数组本身有序，此处校验连续重复
            while ((i + 1 < n) && nums[i] == nums[i + 1]) {
                i++; // 表示跳过一位
            }
            // 元素填充
            nums[cur++] = nums[i];
        }
        // 返回元素个数
        return cur;
    }
}
