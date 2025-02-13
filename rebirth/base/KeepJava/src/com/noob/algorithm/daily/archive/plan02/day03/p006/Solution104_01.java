package com.noob.algorithm.daily.archive.plan02.day03.p006;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_01 {

    /**
     * 思路分析：二叉树的最大深度（即树的高度）
     * 基于层序遍历思路，求分层遍历层数
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 构建队列辅助二叉树遍历
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 计算二叉树层数
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 当层遍历结束，层数累加
            maxDepth++;
        }
        // 返回结果
        return maxDepth;
    }
}
