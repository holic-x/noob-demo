package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;


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
        boolean checkRes = check(root.left, root.right);
        return checkRes;
    }

    /**
     * 递归校验：可以设置两个指针同时遍历：对照比较
     */
    private boolean check(TreeNode p, TreeNode q) {
        // 按照p、q分类讨论

        // ① p、q 均为null
        if (p == null && q == null) {
            return true;
        }

        // ② p、q中只有一个为null 不对称
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        // ③ p、q 中均不为null
        /*
        if (p != null && q != null) {
            // 校验左、右
            return check(p.left, q.right) && check(p.right, q.left) && p.val==q.val;
        }
         */

        return check(p.left, q.right) && check(p.right, q.left) && p.val == q.val;
    }

}
