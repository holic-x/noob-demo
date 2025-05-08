package com.noob.algorithm.daily.plan03.hot100_random.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_01 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * BFS 迭代思路
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 基于迭代思路：根节点到每一层的最左的第1个叶子节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);


        int depth = 0;

        while (!queue.isEmpty()) {

            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();

                // 如果节点为当层的第1个叶子节点，则找到目标节点
                if (node.left == null && node.right == null) {
                    return depth + 1;
                }

                // 处理子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 当层遍历完成
            depth++;
        }

        return depth;
    }

}
