package com.noob.algorithm.solution_archive.dmsxl.twoPointers;

/**
 * 027 移除元素
 */
public class Solution027 {

    /**
     * 双指针思路：left、right
     * left 指向当前等于val的元素位置（用于置换）、right 指向当前不等于val的元素位置
     * 交换元素，直到指针相遇
     */
    public int removeElement(int[] nums, int val) {
        // 定义双指针
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 如果两个指针满足置换条件则进行交换，交换完毕指针靠拢
            if (nums[left] == val && nums[right] != val) {
                // 交换元素
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                // 指针靠拢
                left++;
                right--;
            } else if (nums[left] != val) {
                left++; // left指针前移，寻找下一个可置换的位置
            } else if (nums[right] == val) {
                right--; // right指针前移，寻找下一个可置换的位置
            }
        }
        // 返回不等于val的元素数量
        return left;
    }


}
