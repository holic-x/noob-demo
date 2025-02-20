package com.noob.algorithm.plan_archive.plan02.hot100.day04.p011;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 106 从中序和后序遍历序列构造二叉树 - https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 */
public class Solution106_01 {

    /**
     * 思路分析：基于中序+后序遍历构建二叉树
     * - ① 中序（LDR）：根据根节点值可以确定其在中序遍历的索引位置，随后划分左、右区域
     * - ② 后序（LRD）：范围数组的最后一个节点为根节点（即postR边界）
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = buildHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        return root;
    }


    // 递归构建二叉树
    private TreeNode buildHelper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
        // 基于后序序列构建树，需校验是否越界
        if (postL > postR) {
            return null;
        }

        // 构建节点
        TreeNode node = new TreeNode(postorder[postR]); // 此处设定为闭区间（后序遍历的最后1个元素为D，即根节点）
        // 获取该节点在中序遍历序列中的索引，并基于此获取左子树、右子树的节点个数
        int nodeIdx = getIdx(inorder, node.val);
        int leftLen = nodeIdx - inL; // [inL,nodeIdx-1]为左子树区间
        // 确定递归范围(递归构建左右子树)
        node.left = buildHelper(inorder, inL, nodeIdx - 1, postorder, postL, leftLen + postL - 1);
        node.right = buildHelper(inorder, nodeIdx + 1, inR, postorder, leftLen + postL, postR - 1);
        // 返回构建的节点
        return node;
    }

    // 根据值获取索引
    private int getIdx(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
