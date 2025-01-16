package com.noob.algorithm.daily.plan01.archive.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢222 完全二叉树的节点个数
 */
public class Solution222_02 {

    // 迭代法：基于遍历的思路，计算节点值
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return bfs(root);
    }

    public int bfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        int nodeSize = 0; // 统计节点个数

        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);// 初始化队列
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            nodeSize++;
            // 处理子节点
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        // 返回统计结果
        return nodeSize;
    }
}
