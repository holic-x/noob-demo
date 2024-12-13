package com.noob.algorithm.dmsxl.leetcode.q700;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 700 二叉搜索树中的搜索
 */
public class Solution2 {

    // 递归法
    public TreeNode searchBST(TreeNode root, int val) {
        // 递归出口
        if (root == null) {
            return null;
        }

        // 递归逻辑
        if (root.val == val) {
            return root;
        }

        // 分别递归左右子树
        // TreeNode left = searchBST(root.left, val);
        // TreeNode right = searchBST(root.right, val);
        // return left != null ? left : right;

        /**
         * 利用二叉搜索树的特性来判断要递归哪个子树
         * 当前节点值大于target，递归左子树
         * 当前节点值小于target，递归右子树
         */
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);

    }

}
