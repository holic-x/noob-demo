package com.noob.algorithm.leetcode.q101;


/**
 * 101.堆成二叉树
 */
public class Solution1 {

    public boolean isSymmetric(TreeNode root) {
        // p、q的起始节点均从root开始
        return check(root,root);
    }

    public boolean check(TreeNode p, TreeNode q) {

        // 边界条件检验
        /* 如果p、q都为null */
        if (p == null && q == null) {
            return true;
        }

        /* 如果p、q两者中有一个为null，明显不符合镜像对称要求 */
        if (p == null || q == null) {
            return false;
        }

        /* p、q都不为null，需校验指针对应的节点值，及其子树的值（子树对称可通过递归校验） */
        // if (p != null && q != null) {}
        return p.val==q.val && check(p.left, q.right) && check(p.right, q.left);
    }

}

/* 二叉树节点类 */
class TreeNode {
    int val;         // 节点值
    TreeNode left;   // 左子节点引用
    TreeNode right;  // 右子节点引用

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

