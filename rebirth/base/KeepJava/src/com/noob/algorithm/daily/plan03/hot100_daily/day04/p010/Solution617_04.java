package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 617 合并二叉树 - https://leetcode.cn/problems/merge-two-binary-trees/description/
 */
public class Solution617_04 {

    /**
     * 思路分析：单队列版本的另一种实现思路（用TreeNode[]存储待处理的一组节点）
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return buildHelper(root1, root2);
    }

    // 递归处理思路
    private TreeNode buildHelper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }

        if (node1 != null && node2 == null) {
            return node1;
        }

        if (node1 == null && node2 != null) {
            return node2;
        }

        // 处理节点
        TreeNode mn = new TreeNode(node1.val + node2.val);
        mn.left = buildHelper(node1.left, node2.left);
        mn.right = buildHelper(node1.right, node2.right);
        return mn;
    }

}
