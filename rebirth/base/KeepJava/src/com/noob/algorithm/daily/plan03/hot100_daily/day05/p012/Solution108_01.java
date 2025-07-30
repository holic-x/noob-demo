package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 108 将有序数组转换为二叉搜索树 - https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/
 */
public class Solution108_01 {

    public TreeNode sortedArrayToBST(int[] nums) {

        return buildHelper(nums, 0, nums.length - 1);

    }

    // 定义构造辅助方法:构建[left,right]范围内的树
    private TreeNode buildHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null; // 越界则返回null表示叶子节点
        }

        // 取节点中点进行处理
        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildHelper(nums, left, mid - 1);
        node.right = buildHelper(nums, mid + 1, right);

        // 返回构建的节点
        return node;
    }

}
