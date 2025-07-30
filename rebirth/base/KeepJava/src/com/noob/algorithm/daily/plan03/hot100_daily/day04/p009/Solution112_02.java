package com.noob.algorithm.daily.plan03.hot100_daily.day04.p009;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 112 路径总和 - https://leetcode.cn/problems/path-sum/
 */
public class Solution112_02 {

    /**
     * 思路分析：求[根节点->叶子节点]路径上所有节点值之和等于targetSum的路径，存在则返回true，不存在则返回false
     * - BFS 思路
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // 基于层序遍历思路实现：构建双队列辅助存储
        Queue<TreeNode> nodeQueue = new LinkedList<>(); // 节点存储
        Queue<Integer> valueQueue = new LinkedList<>(); // 相关路径和存储（与节点的遍历保持同步）

        // 初始化队列
        nodeQueue.offer(root);
        valueQueue.offer(root.val);

        // 遍历队列
        while (!nodeQueue.isEmpty()) {
            // 取出节点，验证targetSum
            TreeNode curNode = nodeQueue.poll();
            int curValue = valueQueue.poll();

            // 校验targetSum(叶子节点 且 路径和为targetSum)
            if ((curNode.left == null && curNode.right == null) && curValue == targetSum) {
                return true;
            }

            // 处理节点的左、右节点
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                valueQueue.offer(curValue + curNode.left.val);
            }
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                valueQueue.offer(curValue + curNode.right.val);
            }
        }
        return false;
    }

}