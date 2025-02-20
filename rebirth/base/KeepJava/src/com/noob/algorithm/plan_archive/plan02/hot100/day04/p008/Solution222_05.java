package com.noob.algorithm.plan_archive.plan02.hot100.day04.p008;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_05 {

    /**
     * 思路分析：完全二叉树（除了最底层，其他层都填满）
     * 需要基于完全二叉树的特性来优化算法（例如递归优化：针对不同的树选择不同的遍历方式）
     * 例如满二叉树可以通过公式：2^h^-1 得到所有节点个数， 而对于其他普通的二叉树则选用递归的方式
     * 此处先理解节点个数统计的递归核心:L+R+1
     */
    public int countNodes(TreeNode root) {
        int cnt = dfs(root);
        return cnt;
    }

    // 以当前节点为根节点的子树的节点个数 = L + R + 1
    private int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        /**
         * 递归核心：L+R+1，此处先校验L、R子树是否为满二叉树
         * - 如果为满二叉树，则当前子树节点个数为2^h^-1(直接返回无需继续递归)
         * - 如果为普通二叉树，则当前子树节点个数通过递归统计获取
         */
        // 满二叉树的左右子树深度都相同，因此可以通过左、右指针不断向左、向右遍历得到
        TreeNode leftP = node.left;
        int leftDepth = 0;
        while (leftP != null) {
            leftDepth++;
            leftP = leftP.left; // 指针不断向左
        }

        TreeNode rightP = node.right;
        int rightDepth = 0;
        while (rightP != null) {
            rightDepth++;
            rightP = rightP.right; // 指针不断向右
        }

        // ① 校验子树是否为满二叉树
        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) - 1;
        }

        // ② 普通二叉树则通过递归方式进行统计
        int L = dfs(node.left);// 递归统计左子树
        int R = dfs(node.right); // 递归统计右子树
        // 返回当前子树的节点个数
        return L + R + 1;
    }
}
