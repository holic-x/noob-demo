package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 101 对称二叉树 - https://leetcode.cn/problems/symmetric-tree/description/
 */
public class Solution101_01 {

    /**
     * 思路分析：
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean ans = dfs(root.left, root.right);
        return ans;
    }


    // 递归校验两棵二叉树节点是否对称
    private boolean dfs(TreeNode p, TreeNode q) {
        // p、q 均为null
        if (p == null && q == null) {
            return true;
        }

        // p、q 中只有一个为null
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        // p、q 均不为null,递归校验左右子树的对称性
        return dfs(p.left, q.right) && dfs(p.right, q.left) && p.val == q.val;
    }


}
