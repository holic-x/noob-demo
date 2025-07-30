package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_01 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * BFS 迭代思路: 基于层序遍历思路，从根节点出发到遇到的第1个叶子节点所在层数即为最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        // 遍历节点
        while (!queue.isEmpty()) {

            // 分层遍历
            int curSize = queue.size();

            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();

                // 如果遇到第1个叶子节点则返回其所在层数（即为最小深度）
                if (node.left == null && node.right == null) {
                    return depth + 1;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }

        return -1;
    }

}
