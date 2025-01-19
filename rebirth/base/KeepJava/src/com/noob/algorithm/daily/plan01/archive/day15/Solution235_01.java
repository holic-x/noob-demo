package com.noob.algorithm.daily.plan01.archive.day15;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 235 二叉搜索树的最近公共祖先
 */
public class Solution235_01 {

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        return dfs(node, p, q);
    }

    /**
     * 穷举法思路：（二叉树的最近公共祖先解法）
     * 1.node==null || node==p || node==q : 返回节点return node
     * 2.node!=null 进一步递归判断是在左子树还是右子树
     * - 如果左右子树都有则说明是当前节点：return node
     * - 如果在左子树或者右子树，则返回不为null的那个子树节点
     */
    public TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q) {
            return node;
        }

        // 递归判断最近公共祖先是在左子树还是右子树、还是同时出现在两个子树
        TreeNode findLeft = dfs(node.left, p, q);
        TreeNode findRight = dfs(node.right, p, q);

        if (findLeft != null && findRight != null) {
            return node;
        }

        return findLeft != null ? findLeft : findRight;
    }
}
