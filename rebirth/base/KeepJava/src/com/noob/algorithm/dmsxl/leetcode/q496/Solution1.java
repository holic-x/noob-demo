package com.noob.algorithm.dmsxl.leetcode.q496;

import java.util.Arrays;

/**
 * 496 下一个更大的元素I
 */
public class Solution1 {

    // 暴力循环
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        // 定义结果集
        int[] res = new int[len1];
        Arrays.fill(res, -1); // 默认置为-1

        // 外层循环确定nums1的每个元素，内存循环寻找在nums2中比nums1要大的第1个元素
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (nums1[i] == nums2[j]) { // 因为题中给出数组中不存在重复元素，所以可以确保找到一个唯一的内容
                    for (int k = j + 1; k < len2; k++) {
                        if (nums1[i] < nums2[k]) {
                            res[i] = nums2[k]; // 记录下一个更大的元素
                            break; // 已经寻找到第一个大的元素，跳出内层循环，继续下一个i位置遍历
                        }
                    }
                }
            }
        }

        // 返回结果
        return res;
    }
}
