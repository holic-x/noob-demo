package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 513 寻找树左下角的值 - https://leetcode.cn/problems/find-bottom-left-tree-value/description/
 */
public class Solution513_01 {

    /**
     * 思路分析：给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * 假设二叉树中至少有一个节点
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 基于BFS思路处理：分层校验
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 目标路径
        int target = root.val;

        while (!queue.isEmpty()) {
            // 分层校验
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 如果是当层的第1个节点则记录
                if (i == 0) {
                    target = node.val;
                }

                // 处理子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }

        }
        // 返回最终结果
        return target;
    }
}
