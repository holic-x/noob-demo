package com.noob.algorithm.solution_archive.dmsxl.leetcode.q027;

/**
 * 027 移除元素
 */
public class Solution1 {
    // 覆盖法（参考移动0的思路）：将非val数据依次按顺序覆盖数组
    public int removeElement(int[] nums, int val) {
        int idx = 0; // idx 指向当前要覆盖的元素位置
        for (int i = 0; i < nums.length; i++) {
            // 如果遍历指向元素为非val，则将其移动到前面（覆盖（由于题意不care其他的内容，因此可以直接覆盖））
            if (nums[i] != val) {
                nums[idx] = nums[i]; // 直接覆盖（或者自定义方法进行交换）
                idx++; // idx 指向下一个覆盖的位置
            }
        }
        return idx;
    }
}
