package com.noob.algorithm.solution_archive.dmsxl.leetcode.q104;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 104 二叉树的最大深度
 */
public class Solution1 {
    // 层序遍历：迭代法
    public int maxDepth(TreeNode root) {
        // 判断root为null的情况
        if (root == null) {
            return 0;
        }

        // 定义层数（最大深度）
        int level = 0;

        // 构建辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化队列

        // 分层遍历队列，统计层数
        while (!queue.isEmpty()) {
            // 分层统计
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode cur = queue.poll();
                // 如果左右子节点不为空，分别入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层遍历结束，记录层数
            level++;
        }
        // 遍历层数即为二叉树的最大深度
        return level;
    }
}
