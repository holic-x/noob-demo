package com.noob.algorithm.solution_archive.dmsxl.leetcode.q189;

/**
 * 🟡189 轮转数组
 */
public class Solution1 {

    // 三次反转
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len; // 获取真正需要的轮转次数

        // 基于反转的概念：对整个数组进行反转，对前k反转，对后n-k个元素进行反转
        reverse(nums, 0, len - 1);// 对整个数组进行反转[0,len-1]
        reverse(nums, 0, k - 1); // 对前k个元素进行反转[0,k-1]
        reverse(nums, k, len - 1); // 对后n-k个元素进行反转[k,n-1]
    }

    // 定义方法：限定对指定区域范围的数组元素进行反转
    public void reverse(int[] nums, int start, int end) { // 此处限定取值范围为[start,end]
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            // 反转完成，指针靠拢
            start++;
            end--;
        }
    }

}
