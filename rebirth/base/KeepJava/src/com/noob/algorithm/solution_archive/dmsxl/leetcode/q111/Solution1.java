package com.noob.algorithm.solution_archive.dmsxl.leetcode.q111;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111 二叉树的最小深度
 */
public class Solution1 {

    /**
     * 层序遍历：分层遍历每一层元素，
     */
    public int minDepth(TreeNode root) {
        // root为null校验
        if (root == null) {
            return 0;
        }
        // 构建辅助队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 分层遍历
        int depth = 0; // 记录当前遍历层数
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            depth++; // 遍历当层层数
            for (int i = 0; i < curSize; i++) {
                // 取出元素
                TreeNode cur = queue.poll();

                // 如果当前节点为"当层的叶子结点"即没有左右节点，则直接返回（找到最小深度的节点了）
                if (cur.left == null && cur.right == null) {
                    return depth;
                }

                // 左右节点存在则入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return 0;
    }

}
