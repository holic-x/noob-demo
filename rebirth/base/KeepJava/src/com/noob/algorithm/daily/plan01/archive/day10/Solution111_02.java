package com.noob.algorithm.daily.plan01.archive.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 111 二叉树的最小深度
 */
public class Solution111_02 {

    // BFS 层次遍历：从上到下、从左到右，搜索到第一个叶子结点直接返回层数（即为最短路径）
    public int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 记录遍历层数
        int level = 0;

        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            // 计算当层节点个数
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode cur = queue.poll();
                // 处理节点（如果为叶子节点，则直接返回）
                if (cur.left == null && cur.right == null) {
                    return level + 1; // 返回的是每个路径的节点个数，因此此处返回的是层数+1
                }

                // 节点入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层遍历结束
            level++;
        }
        return 0;
    }
}
