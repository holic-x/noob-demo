package com.noob.algorithm.leetcode.common150.q236;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

/**
 * 236 二叉树的最近公共祖先
 */
public class Solution1 {
    /**
     * 分类讨论：遍历节点进行讨论
     * 1.node==null || node == p || node == q 则返回node
     * 2.判断其是在左子树还是右子树：
     *  - 如果左右子树都找到，则当前node即为公共节点
     *  - 如果只有左子树找到，则返回findLeft（递归左子树的结果）
     *  - 如果只有右子树找到，则返回findRight（递归右子树的结果）
     *  - 如果左右子树都没找到，返回null
     */
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        // 递归退出条件
        if (node == null) {
            return null;
        }
        // 1.如果当前节点node为p或q，则node即为最小公共节点（结合案例可分析）
        if (node == p || node == q) {
            return node;
        }
        // 2.其他情况判断：继续判断公共节点是在左子树还是右子树
        TreeNode findLeft = lowestCommonAncestor(node.left, p, q);
        TreeNode findRight = lowestCommonAncestor(node.right, p, q);
        // 判断左右子树的查找情况（要么在左子树、要么在右子树，要么左右子树都找到的情况下当前节点就是最小公共节点）
        if (findLeft != null && findRight != null) {
            return node; // 左右子树都找到了，则node就是当前的公共节点
        }
        if (findLeft != null) {
            return findLeft; // 只有左子树找到了
        }
        if (findRight != null) {
            return findRight; // 只有右子树找到了
        }
        return null; // 左右子树都没找到
    }
}
