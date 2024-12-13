package com.noob.algorithm.dmsxl.leetcode.q222;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 完全二叉树的节点个数（222）
 */
public class Solution1 {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftCnt = dfs(node.left);
        int rightCnt = dfs(node.right);
        return leftCnt + rightCnt + 1;
    }

}
