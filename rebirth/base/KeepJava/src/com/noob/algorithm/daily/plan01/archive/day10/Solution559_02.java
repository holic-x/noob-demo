package com.noob.algorithm.daily.plan01.archive.day10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢559 N叉树的最大深度
 */
public class Solution559_02 {

    public int maxDepth(Node root) {
        return bfs(root);
    }

    // BFS： 基于 N 叉树的层序遍历，计算层数
    public int bfs(Node node) {
        if (node == null) {
            return 0;
        }

        // 构建队列辅助遍历
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node); // 初始化

        int level = 0; // 计算层数

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                // 取出当前节点
                Node cur = queue.poll();

                // 将节点的子节点列表入队
                for (Node child : cur.children) {
                    if (child != null) {
                        queue.offer(child);
                    }
                }
            }
            level++; // 当层处理完成，统计level
        }

        // 返回层数
        return level;
    }
}


