package com.noob.algorithm.solution_archive.dmsxl.leetcode.q718;

/**
 * 718 最长重复子数组
 */
public class Solution1 {

    /**
     * 暴力枚举
     */
    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int maxLen = 0;
        // 遍历A数组，确定每个起点
        for (int i = 0; i < n1; i++) {
            // 遍历B数组，同步比较最长前缀
            for (int j = 0; j < n2; j++) {
                if (nums1[i] == nums2[j]) { // 找到起点，开始计数判断最长前缀
                    int k = 0;
                    while (i + k < n1 && j + k < n2 && nums1[i + k] == nums2[j + k]) {
                        k++;
                    }
                    maxLen = Math.max(maxLen, k); // 更新以每个可能的元素为起点的最长前缀
                    k = 0; // 重置计数器,等待下一个点的校验
                }
            }
        }
        // 返回结果
        return maxLen;
    }

}
