package com.noob.algorithm.solution_archive.dmsxl.leetcode.q236;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 236 最近公共祖先
 */
public class Solution1 {

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        // 递归出口
        if (node == null || node == p || node == q) {
            return node; // node为null 或者 node为p、q中的其中一个节点，则当前节点就是最近公共祖先
        }

        // 分别递归查找左右子树的最近公共祖先
        TreeNode leftNode = lowestCommonAncestor(node.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(node.right, p, q);

        // 根据左右子树查找结果来确定最近公共祖先

        // 如果左、右子树都找到了，则当前节点就是最近公共祖先
        if (leftNode != null && rightNode != null) {
            return node;
        }

        // 如果左右子树都没有找到，则返回null
        if (leftNode == null && rightNode == null) {
            return null;
        }

        // 如果左子树或者右子树找到了，则返回找到的那个
        return leftNode != null ? leftNode : rightNode;
    }
}
