package com.noob.algorithm.common150.q27;

/**
 * 27 移除元素
 */
public class Solution2 {

    // 使用原地方法移除元素
    public int removeElement(int[] nums, int val) {
        // 双指针：把所有的val移动到右边
        int left = 0, right = nums.length - 1;
        // left 找val元素，right 找不是val的元素,指针相遇遍历结束
        while (left <= right) {
            if (nums[left] == val) {
                // 判断当前右边位置，找一个可以交换的位置进行交换
                if (nums[right] == val) {
                    // 如果right位置元素为val，则继续往前找
                    right--;
                } else {
                    // 如果left位置元素不为val，则可进行一次交换操作，交换完成左指针继续后移
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    // 交换完成，左指针右移
                    left++;
                }
            } else {
                // 如果left位置元素不为val，则继续往下找
                left++;
            }
        }
        return left;
    }


}
