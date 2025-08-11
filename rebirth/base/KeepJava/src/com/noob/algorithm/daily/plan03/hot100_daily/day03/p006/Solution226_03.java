package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 226 翻转二叉树 - https://leetcode.cn/problems/invert-binary-tree/description/
 */
public class Solution226_03 {

    /**
     * 思路分析：给出root，翻转二叉树，并返回根节点
     * - BFS 思路：层序遍历是从上到下、从左到右，此处通过遍历每个节点交换其左右子节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 交换其左右子节点
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            // 将左右子节点入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // 返回处理后的节点
        return root;
    }

}
