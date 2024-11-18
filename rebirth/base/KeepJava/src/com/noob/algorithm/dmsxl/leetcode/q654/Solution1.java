package com.noob.algorithm.dmsxl.leetcode.q654;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 654 最大二叉树
 */
public class Solution1 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode node = buildHelper(nums, 0, nums.length - 1); // 对照取闭区间
        return node;
    }

    // 根据指定区间构建树 root、left、right
    public TreeNode buildHelper(int[] nums, int left, int right) {

        // 基于前序思路构建树，此处验证区间有效性
        if (left > right) {
            return null;
        }

        // root 构建：获取当前指定区间的最大值max及其索引位置maxIdx
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        for (int i = left; i <= right; i++) { // 此处如果取闭区间，注意区间传参
            if (nums[i] > max) {
                // 更新最大值和相应索引
                max = nums[i];
                maxIdx = i;
            }
        }

        // 根据root节点索引划分左右区间：L[left,maxIdx-1]、D(maxIdx)、R[maxIdx+1,right](结合题意：最大值左侧为左区间、最大值右侧为右区间)
        TreeNode root = new TreeNode(max);
        root.left = buildHelper(nums, left, maxIdx - 1);
        root.right = buildHelper(nums, maxIdx + 1, right);

        // 返回构建的节点
        return root;
    }

}
