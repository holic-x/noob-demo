package com.noob.algorithm.plan_archive.plan02.hot100.day05.p013;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 235 二叉搜索树的最近公共祖先 - https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 */
public class Solution235_02 {

    /**
     * 思路分析：自顶向下寻找的满足在[pVal,qVal]区间（p、q大小未确定，也可能是[qVal,pVal]）的第1个节点即为公共节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    // 递归
    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q) {
            return node;
        }

        // 判断节点值与限定区间的关系[pVal,qVal] [qVal,pVal]
        int nodeVal = node.val, pVal = p.val, qVal = q.val;
        if ((nodeVal >= pVal && nodeVal <= qVal) || (nodeVal <= pVal && nodeVal >= qVal)) {
            return node;
        } else if (nodeVal < pVal || nodeVal < qVal) {
            // 需要往右递归方能接近区间
            return dfs(node.right, p, q);
        } else if (nodeVal > pVal || nodeVal > qVal) {
            // 需要往左递归方能接近区间
            return dfs(node.left, p, q);
        }
        return null;
    }

}
