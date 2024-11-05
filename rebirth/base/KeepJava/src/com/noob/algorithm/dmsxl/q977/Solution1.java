package com.noob.algorithm.dmsxl.q977;

/**
 * 977 有序数组的平方
 */
public class Solution1 {
    // 计算平方 + 逆序存放
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        // 定义数组存放数组元素
        int[] newNums = new int[len];
        int idx = len - 1; // idx 存放指定下标元素

        // 定义头尾指针
        int start = 0, end = len - 1;

        // 原数组本身有序，因此需比较平方和大小，然后将较大的元素从newNums队尾开始遍历
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[start]) >= Math.abs(nums[end])) {
                newNums[idx] = nums[start] * nums[start];
                start++;
                idx--; // 逆序存放，指针前移
            } else {
                newNums[idx] = nums[end] * nums[end];
                end--;
                idx--; // 逆序存放，指针前移
            }
        }
        // 返回构建的数组
        return newNums;
    }
}
