package com.noob.algorithm.dmsxl.leetcode.q104;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 104 二叉树的最大深度
 */
public class Solution2 {
    /**
     * 递归法 DFS 思路：
     * 节点的最大深度：max{ maxDepth(node.left),maxDepth(node.right) } + 1 即为左右子树的最大深度+当前节点（1层）
     */
    public int maxDepth(TreeNode root) {
        // 递归出口
        if (root == null) {
            return 0;
        }
        // 递归过程：计算左右子节点的最大深度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // 获取当前子树的最大深度
        return Math.max(left, right) + 1;
    }
}
