package com.noob.algorithm.daily.plan03.hot100_daily.day05.p013;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 235 二叉搜索树的最近公共祖先 - https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 */
public class Solution235_01 {

    /**
     * 思路分析：
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 确保p、q的范围关系
        if (p.val < q.val) { // [p,q]
            return dfs(root, p, q);
        }
        // [q,p]
        return dfs(root, q, p);

        /*
        // 确保 small.val <= large.val
        TreeNode small = p.val < q.val ? p : q;
        TreeNode large = p.val < q.val ? q : p;
        return dfs(root, small, large);
         */
    }

    // dfs: [sNode,lNode] =》 sNode 较小的节点，lNode 较大的节点
    private TreeNode dfs(TreeNode node, TreeNode sNode, TreeNode lNode) {
        if (node == null || node == sNode || node == lNode) {
            return node;
        }

        // [sNode,lNode] 范围检索：自顶向下找到的第1个节点一定满足LCA，也就是说LCA一定在[sNode,lNode]的检索范围内
        int curVal = node.val;
        if (curVal < sNode.val) { // 校验(-∞,sNode)
            // 向右侧靠拢
            return dfs(node.right, sNode, lNode);
        }
        if (curVal > lNode.val) { // 校验(lNode,+∞)
            // 向左侧靠拢
            return dfs(node.left, sNode, lNode);
        }

        // 返回当前节点（满足[sNode,lNode]范围）
        return node;
    }

}
