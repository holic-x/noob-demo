package com.noob.algorithm.dmsxl.leetcode.q101;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 101 对称二叉树
 */
public class Solution1 {
    public boolean isSymmetric(TreeNode root) {
        // 调用递归方法，p、q 初始都从root开始
        // return check(root, root);
        return root == null || check(root.left, root.right); // 短路思路：root不为null才会执行左右子树对称判断
    }

    /**
     * 递归检查两个节点是否对称（此处传入两个要进行校验的对称节点）
     * 1.p、q 都为 null
     * 2.p、q 中某一个为 null
     * 3.p、q 都不为null，进一步校验两者的值是否相等，以及各自的左右子树是否也对称
     * - 此处左右子树是否对称是 p.left,q.right 对称节点比较、p.right,q.left 对称节点比较
     */
    public boolean check(TreeNode p, TreeNode q) {
        // 1.p、q 都为 null
        if (p == null && q == null) {
            return true; // 对称
        }

        // 2.p、q 中某一个为 null
        if (p == null || q == null) {
            return false; // 显然不对称
        }

        // 3.p、q 都不为null，进一步校验两者的值是否相等，以及各自的左右子树是否也对称
        return (p.val == q.val) && check(p.left, q.right) && check(p.right, q.left);
    }
}
