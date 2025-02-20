package com.noob.algorithm.leetcode.common150.q108;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.Arrays;

/**
 * 108 将有序数组转化为二叉搜索树
 * 平衡二叉搜索树的特点，其中序遍历结果是连续升序序列
 */
public class Solution1 {
    // 数组本身有序，将中点作为根节点，中点左侧的作为左子树、中点右侧的作为右子树
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums);
    }

    public TreeNode build(int[] nums) {
        // 如果传入的nums为空或者长度为0则不需要构建
        if (nums == null || nums.length == 0) {
            return null;
        }

        // 计算中点索引位置
        int mid = nums.length / 2;

        // 创建根节点
        TreeNode root = new TreeNode(nums[mid]);
        // 分别构建左右节点
        root.left = build(Arrays.copyOfRange(nums, 0, mid));
        root.right = build(Arrays.copyOfRange(nums, mid + 1, nums.length));

        // 返回构建的节点
        return root;
    }

}
