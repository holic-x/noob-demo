package com.noob.algorithm.daily.archive.plan01.day13;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡654 最大二叉树
 */
public class Solution654_01 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = buildHelper(nums, 0, nums.length - 1);
        return root;
    }

    /**
     * 递归构建二叉树
     * @param nums 数组列表
     * @param left 区间左界限
     * @param right 区间右界限
     * @return
     */
    public TreeNode buildHelper(int[] nums, int left, int right) {
        // 递归出口
        if (left > right) {
            return null;
        }

        // 构建节点
        int[] max = getMax(nums, left, right);
        int nodeIdx = max[0];
        int nodeVal = max[1];
        TreeNode node = new TreeNode(nodeVal);

        // 递归构建左、右节点
        node.left = buildHelper(nums, left, nodeIdx - 1);
        node.right = buildHelper(nums, nodeIdx + 1, right);

        // 返回构建的节点
        return node;
    }

    // 获取指定区间范围的nums的最大值
    public int[] getMax(int[] nums, int left, int right) {
        int maxVal = nums[left]; // Integer.MIN
        int maxIdx = left; // -1
        for (int i = left; i <= right; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIdx = i;
            }
        }
        // 返回数组元素{maxIdx,maxVal}分表表示当前数组指定区间范围内的最大值和其对应索引
        return new int[]{maxIdx, maxVal};
    }

}
