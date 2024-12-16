package com.noob.algorithm.dmsxl.leetcode.q283;

/**
 * 🟢 283 移动零
 */
public class Solution2 {
    // 双指针法(首尾指针、对向遍历❌❌会打乱元素本身的顺序)
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        // 定义辅助数组存储结果
        int left = 0, right = len - 1;  // 定义双指针分别从头尾出发（left寻找为0的元素，right寻找不为0的元素，然后进行交换）
        // 遍历数组
        while (left < right) {
            // 满足条件则可以将两边元素进行交换
            if (nums[left] == 0 && nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                // 交换完成，指针继续移动
                left++;
                right--;
            }else{
                // left 用于寻找元素0（遇到非0元素则跳过）
                if (nums[left] != 0) {
                    left++;
                }
                // right 用于寻找非0元素（遇到0元素则跳过）
                if (nums[right] == 0) {
                    right--;
                }
            }
        }
    }
}
