package com.noob.algorithm.plan_archive.plan01.day16;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢108 将有序数组转化为二叉搜索树
 */
public class Solution108_01 {

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = buildHelper(nums, 0, nums.length - 1);
        return root;
    }

    // 基于有序序列递归构建二叉搜索树
    public TreeNode buildHelper(int[] nums, int left, int right) {
        // 指针越界则返回null
        if (left > right) {
            return null;
        }

        // 构建节点
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        // 递归构建左右子树
        node.left = buildHelper(nums, left, mid - 1);
        node.right = buildHelper(nums, mid + 1, right);

        // 返回构建节点
        return node;
    }

    public static void main(String[] args) {
        // 定义有序序列
        int[] nums = new int[]{1, 2, 3, 4, 5};
        Solution108_01 s = new Solution108_01();
        s.sortedArrayToBST(nums);
    }
}
