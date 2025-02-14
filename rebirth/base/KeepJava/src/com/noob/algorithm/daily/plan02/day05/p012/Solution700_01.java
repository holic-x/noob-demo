package com.noob.algorithm.daily.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 700 二叉搜索树中的搜索 - https://leetcode.cn/problems/search-in-a-binary-search-tree/description/
 */
public class Solution700_01 {


    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode findNode = search(root, val);
        return findNode;
    }

    /**
     * 递归搜索：基于DLR顺序递归遍历
     */
    private TreeNode search(TreeNode node, int val) {
        if (node == null) {
            return null;
        }

        if (node.val == val) {
            return node;
        }

        // 递归遍历左、右子树（只要两者中存在即满足，要么在左边、要么在右边）
        TreeNode findLeft = search(node.left, val);
        TreeNode findRight = search(node.right, val);

        return findLeft == null ? findRight : findLeft;
    }
}
