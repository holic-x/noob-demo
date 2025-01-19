package com.noob.algorithm.daily.plan01.archive.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢222 完全二叉树的节点个数
 */
public class Solution222_01 {

    // 递归法：基于遍历的思路，计算节点值
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }
        int leftNodeCnt = dfs(node.left);
        int rightNodeCnt = dfs(node.right);
        return leftNodeCnt + rightNodeCnt + 1;
    }
}
