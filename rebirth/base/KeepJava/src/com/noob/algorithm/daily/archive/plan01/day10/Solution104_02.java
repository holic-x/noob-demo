package com.noob.algorithm.daily.archive.plan01.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 104 二叉树的最大深度
 */
public class Solution104_02 {

    // 迭代法
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = bfs(root);
        return maxDepth;
    }

    // 层序遍历思路：最大深度即计算树的高度，基于层序遍历即计算分层数
    public int bfs(TreeNode node) {
        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        // 定义层数
        int maxDepth = 0;
        // 遍历队列
        while (!queue.isEmpty()) {
            // 获取当层节点个数，处理当层数据
            int cnt = queue.size();
            while (cnt-- > 0) {
                // 取出节点处理
                TreeNode cur = queue.poll();
                // 处理左右节点
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层处理结束，累计层数
            maxDepth++;
        }
        // 返回结果
        return maxDepth;
    }

}
