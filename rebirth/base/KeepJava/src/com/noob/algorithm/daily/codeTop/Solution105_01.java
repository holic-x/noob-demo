package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 105 从前序遍历和中序遍历序列中构造二叉树 - https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 */
public class Solution105_01 {

    /**
     * 前序遍历：DLR 基于DLR序列定位节点位置（在LDR的位置）
     * 中序遍历：LDR 获取到D的位置，随后划分左右子区间
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = builderHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return root;
    }

    // 定义辅助构造方法
    public TreeNode builderHelper(int[] preorder, int[] inorder, int preL, int preR, int inL, int inR) {
        if (preL > preR) {
            return null;
        }
        // 寻找D的位置
        int idx = getIdx(inorder, preorder[preL]);
        TreeNode node = new TreeNode(preorder[preL]);
        // 计算左子树的节点个数
        int leftCnt = idx - inL; // (idx-1-inL+1)
        // 递归构建左右子树
        node.left = builderHelper(preorder, inorder, preL + 1, preL + leftCnt, inL, idx - 1);
        node.right = builderHelper(preorder, inorder, preL + 1 + leftCnt, preR, idx + 1, inR);
        // 返回构建节点
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
