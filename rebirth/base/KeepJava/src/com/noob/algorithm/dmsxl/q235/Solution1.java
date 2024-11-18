package com.noob.algorithm.dmsxl.q235;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 235 二叉搜索树的最近公共祖先
 */
public class Solution1 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    // 深度优先遍历
    public TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        // 如果当前节点值均大于p、q，则递归左子树（往左找才可能靠近p、q）
        if (node.val > p.val && node.val > q.val) {
            return dfs(node.left, p, q);
        }

        // 如果当前节点值均小于p、q，则递归右子树（往右找才可能靠近p、q）
        if (node.val < p.val && node.val < q.val) {
            return dfs(node.right, p, q);
        }

        // 如果当前节点值在指定区间[p,q]\[q,p]则返回当前节点
        return node;
    }
}
