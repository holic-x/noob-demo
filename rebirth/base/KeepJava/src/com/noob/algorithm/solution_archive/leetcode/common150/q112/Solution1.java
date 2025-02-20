package com.noob.algorithm.solution_archive.leetcode.common150.q112;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 112 路径之和
 */
public class Solution1 {
    /**
     * 基于广度优先遍历的思路：BFS 借助队列存取
     * - 节点队列：存储每个遍历的节点
     * - 路径和队列：存储root到每个遍历的节点的路径和
     * 遍历的过程中判断root到leaf的节点是否满足targetSum
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 空节点判断
        if (root == null) {
            return false;
        }
        // 构建队列存储
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        Deque<Integer> valQueue = new LinkedList<>();
        valQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            // 取出节点，然后按层次遍历
            TreeNode node = nodeQueue.poll();
            int cur = valQueue.poll();
            // 判断是否为叶子节点以及路径和是否满足要求
            if (node.left == null && node.right == null) {
                // 如果为叶子节点（左右节点为null），则进一步判断是否存在满足条件的target
                if (cur == targetSum) {
                    return true;
                }
            }
            // 非叶子结点则继续判断并加入队列
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(cur + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(cur + node.right.val);
            }
        }
        return false;
    }

}
