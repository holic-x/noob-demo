package com.noob.algorithm.solution_archive.leetcode.common150.q004;

import com.noob.algorithm.solution_archive.leetcode.common150.q034.Solution;

import java.math.BigDecimal;

/**
 * 004 寻找两个正序数组的中位数
 */
public class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeNums = merge(nums1, nums2);

        int len = mergeNums.length;
        if (len % 2 == 0) {
            // 偶数
            int left = 0, right = len - 1;
            int mid = left + (right - left) / 2;
            return ((double) mergeNums[mid] + (double)mergeNums[mid+1]) / 2; // 需将int转为double做除法
        } else {
            // 奇数
            return mergeNums[mergeNums.length / 2];
        }
    }

    // 合并两个有序数组
    public int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        // 定义数组存储合并后的内容
        int[] nums = new int[len1 + len2];
        // 定义数组指针，分别装箱nums、nums1、nums2当前遍历位置
        int cur = 0, p1 = 0, p2 = 0;
        while (p1 < len1 && p2 < len2) {
            // 如果p1指向元素较小则加入nums1[p1]
            if (nums1[p1] < nums2[p2]) {
                nums[cur] = nums1[p1];
                // 指针后移
                cur++;
                p1++;
            } else {
                nums[cur] = nums2[p2];
                // 指针后移
                cur++;
                p2++;
            }
        }

        // 判断是否有多余长度的元素需补充
        while (p1 < len1) {
            nums[cur] = nums1[p1];
            cur++;
            p1++;
        }
        while (p2 < len2) {
            nums[cur] = nums2[p2];
            cur++;
            p2++;
        }

        // 返回合并后的有序数组
        return nums;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.findMedianSortedArrays(nums1, nums2));
    }
}
