package com.noob.algorithm.plan_archive.plan02.hot100.day03.p006;


import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 226 翻转二叉树 - https://leetcode.cn/problems/invert-binary-tree/description/
 */
public class Solution226_01 {

    /**
     * 思路分析：基于层序遍历，对于遍历的每个节点，交换其左右子节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出节点，交换其左右子节点
            TreeNode node = queue.poll();
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            node.left = rightNode;
            node.right = leftNode;
            // 将节点入队
            if (leftNode != null) {
                queue.offer(leftNode);
            }
            if (rightNode != null) {
                queue.offer(rightNode);
            }
        }
        // 返回处理后的结果
        return root;
    }
}
