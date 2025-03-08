package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_02 {

    /**
     * 思路分析：
     * bfs 思路：求树的层数
     */
    public int maxDepth(TreeNode root) {
        return bfs(root);
    }

    public int bfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int depth = 0;
        // 定义队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层遍历结束
            depth++;
        }
        return depth;
    }

}
