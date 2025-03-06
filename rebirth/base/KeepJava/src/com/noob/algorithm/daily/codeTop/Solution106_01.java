package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 106 从后序遍历和中序遍历序列中构造二叉树 - https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class Solution106_01 {

    /**
     * 后序遍历：LRD 基于LRD序列定位节点位置（在LDR的位置）
     * 中序遍历：LDR 获取到D的位置，随后划分左右子区间
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode node = builderHelper(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
        return node;
    }

    // 定义辅助构造方法
    public TreeNode builderHelper(int[] postorder, int[] inorder, int postL, int postR, int inL, int inR) {
        if (postL > postR) {
            return null;
        }
        // 构建节点
        TreeNode node = new TreeNode(postorder[postR]);
        int idx = getIdx(inorder, postorder[postR]);
        // 获取左子节点个数
        int leftCnt = idx - inL; // idx-1 - inL + 1

        // 递归构建左右子树
        node.left = builderHelper(postorder, inorder, postL, postL + leftCnt - 1, inL, idx - 1);
        node.right = builderHelper(postorder, inorder, postL + leftCnt, postR - 1, idx + 1, inR);
        // 返回构建的节点
        return node;
    }

    private int getIdx(int[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
