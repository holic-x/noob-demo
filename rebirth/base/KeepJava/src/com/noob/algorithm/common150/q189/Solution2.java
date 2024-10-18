package com.noob.algorithm.common150.q189;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 189 轮转数组
 */
public class Solution2 {
    // 反转法(自定义反转逻辑)
    public void rotate(int[] nums, int k) {
        /**
         * 此处轮转的体现效果可以理解为先对数组进行反转，然后分别对前k个元素反转、对后n-k个元素反转
         * 此外还需注意轮转次数k和nums长度的关系（以len为一个轮次）
         */
        k = k % nums.length;

        // 反转整个数组
        reverse(nums, 0, nums.length - 1);

        // 反转数组的前k个元素
        reverse(nums, 0, k - 1);

        // 反转数组的后N-K个元素
        reverse(nums, k, nums.length - 1);

    }

    // 数组反转：原地反转（双指针）
    public void reverse(int[] nums, int start, int end) {
        // int start = 0,end = nums.length-1;
        while (start < end) {
            // 交换当前指针元素
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            // 指针移动
            start++;
            end--;
        }
    }
}
