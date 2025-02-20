package com.noob.algorithm.plan_archive.plan02.hot100.day03.p007;


import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 101 对称二叉树 - https://leetcode.cn/problems/symmetric-tree/description/
 */
public class Solution101_01 {

    public boolean isSymmetric(TreeNode root) {
        // root 校验
        if (root == null) {
            return true;
        }

        // 调用递归方法进行轴对称校验
        return valid(root.left, root.right);
    }

    // 递归遍历校验对称性(根据p、q是否为null进行校验)
    private boolean valid(TreeNode p, TreeNode q) {
        // ① p、q均为null
        if (p == null && q == null) {
            return true;
        }
        // ② p、q中只有1个为null
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        // ③ p、q均不为null
        if (p != null && q != null) {
            // 校验值及其子节点是否对称
            if (p.val != q.val) {
                return false; // 值不相同，必然不对称
            } else {
                // 值相同，则需递归校验其左、右子节点是否对称
                return p.val == q.val && valid(p.left, q.right) && valid(p.right, q.left);
            }
        }
        // 其余情况
        return false;
    }
}
