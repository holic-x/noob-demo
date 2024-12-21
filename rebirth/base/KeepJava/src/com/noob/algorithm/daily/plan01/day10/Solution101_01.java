package com.noob.algorithm.daily.plan01.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢 101 对称二叉树
 */
public class Solution101_01 {

    /**
     * 递归法：结合对称二叉树特性处理
     * 基于dfs思路：可以用两个指针节点进行遍历，看每个指针指向的位置是否匹配
     */
    public boolean isSymmetric(TreeNode root) {
        // 调用递归方法处理
        return dfs(root, root);
    }

    /**
     * 对同一棵树用两个指针分别进行遍历，校验节点对应位置是否匹配。根据 p、q进行null判断
     */
    public boolean dfs(TreeNode p, TreeNode q) {
        // ① 两个节点如果都为null则匹配
        if (p == null && q == null) {
            return true;
        }
        // ② 如果只有一个节点为null则不匹配
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        // ③ 如果两个都不为null，则校验值是否匹配
        if (p != null && q != null) {
            if (p.val != q.val) { // 如果校验的两个节点均不为空，则校验值是否匹配，如果不匹配则返回false
                return false;
            }
        }

        // 递归处理节点：校验对称性（各自的左子树与右子树交叉匹配）
        boolean validLeft = dfs(p.left, q.right);
        boolean validRight = dfs(p.right, q.left);
        return validLeft && validRight;
    }
}
