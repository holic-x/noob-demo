package com.noob.algorithm.daily.plan03.hot100_template.day04.p011;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 654 最大二叉树 - https://leetcode.cn/problems/maximum-binary-tree/description/
 */
public class Solution654_01 {

    /**
     * 思路分析：
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildHelper(nums, 0, nums.length - 1);
    }


    private TreeNode buildHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 获取当前返回内数组的最大值，然后基于这个最大值位置递归构建左、右子树
        int[] max = getMax(nums, left, right);
        int maxIdx = max[0], maxVal = max[1];

        // 构建节点
        TreeNode node = new TreeNode(maxVal);
        node.left = buildHelper(nums, left, maxIdx - 1);
        node.right = buildHelper(nums, maxIdx + 1, right);

        // 返回构建结果
        return node;
    }

    private int[] getMax(int[] nums, int left, int right) {
        // int[] max = {maxIdx,maxVal};
        int[] max = new int[]{-1, -1};
        for (int i = left; i <= right; i++) {
            if (max[1] <= nums[i]) {
                // 更新最大值映射关系
                max = new int[]{i, nums[i]};
            }
        }
        // 返回查找到的max
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        Solution654_01 solution = new Solution654_01();
        solution.constructMaximumBinaryTree(nums);
    }

}
