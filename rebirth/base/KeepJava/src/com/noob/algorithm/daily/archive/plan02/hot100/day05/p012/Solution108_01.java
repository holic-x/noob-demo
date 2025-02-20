package com.noob.algorithm.daily.archive.plan02.hot100.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 108 将有序数组转换为二叉搜索树 - https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/
 */
public class Solution108_01 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildHelper(nums, 0, nums.length - 1);
    }

    // 递归构建树
    private TreeNode buildHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        // 构建节点（每次选择中点位置构建，然后递归构建左、右子节点）
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildHelper(nums, start, mid - 1);
        node.right = buildHelper(nums, mid + 1, end);

        // 返回构建的节点
        return node;
    }


}
