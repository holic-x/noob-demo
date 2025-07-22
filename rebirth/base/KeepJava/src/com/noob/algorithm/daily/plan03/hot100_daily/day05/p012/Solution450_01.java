package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 450 删除二叉搜索树中的节点 - https://leetcode.cn/problems/delete-node-in-a-bst/description/
 */
public class Solution450_01 {

    /**
     * 思路分析：
     */
    public TreeNode deleteNode(TreeNode root, int key) {

        return null;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        Solution450_01 s = new Solution450_01();
        s.deleteNode(node1, 3);
    }
}
