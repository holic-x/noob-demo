package com.noob.algorithm.plan_archive.plan02.hot100.day04.p011;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 654 最大二叉树 - https://leetcode.cn/problems/maximum-binary-tree/description/
 */
public class Solution654_01 {

    /**
     * 思路分析：每次选择区间范围内的最大值作为D，其左侧作为左子树，其右侧作为右子树
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = buildHelper(nums, 0, nums.length - 1);
        return root;
    }

    // 递归构建
    private TreeNode buildHelper(int[] nums, int left, int right) {
        // 校验是否越界
        if (left > right) {
            return null;
        }

        // 构建节点（择选区间内的最大值）
        int[] maxItem = getMax(nums, left, right);
        int nodeVal = maxItem[0], nodeIdx = maxItem[1];
        TreeNode node = new TreeNode(nodeVal);
        // 递归构建子树
        node.left = buildHelper(nums, left, nodeIdx - 1);
        node.right = buildHelper(nums, nodeIdx + 1, right);
        // 返回构建节点
        return node;
    }

    // 获取指定区间的最大值及其索引（[left,right]）范围new int[]{val,idx}
    private int[] getMax(int[] nums, int left, int right) {
        int maxVal = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = left; i <= right; i++) {
            if (maxVal < nums[i]) {
                maxVal = nums[i];
                idx = i;
            }
        }
        // 返回
        return new int[]{maxVal, idx};
    }
}
