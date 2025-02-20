package com.noob.algorithm.solution_archive.leetcode.common150.q101;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

/**
 * 101 对称二叉树
 */
public class Solution1 {

    /**
     * 转换题意：迭代思路
     * 判断左右子树是否对称
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    /**
     * 递归判断两个树是否对称
     */
    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }


        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

}
