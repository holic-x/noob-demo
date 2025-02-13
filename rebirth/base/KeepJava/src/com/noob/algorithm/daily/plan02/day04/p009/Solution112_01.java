package com.noob.algorithm.daily.plan02.day04.p009;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 112 路径总和
 */
public class Solution112_01 {

    /**
     * 思路分析：判断是否存在路径和（根节点到叶子节点）为targetSum的路径
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 构建双队列辅助遍历
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        LinkedList<Integer> pathSumQueue = new LinkedList<>(); // 定义路径和队列
        pathSumQueue.offer(root.val);

        // 遍历队列
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int curPathSum = pathSumQueue.poll();

            // 校验是否到达叶子节点
            if (node.left == null && node.right == null) {
                // 达到叶子节点，判断targetSum是否满足
                if (curPathSum == targetSum) {
                    return true;
                }
            }


            // 处理左子节点
            if (node.left != null) {
                // 节点处理
                nodeQueue.offer(node.left);
                // 路径和处理
                pathSumQueue.offer(curPathSum + node.left.val);
            }
            // 处理右子节点
            if (node.right != null) {
                // 节点处理
                nodeQueue.offer(node.right);
                // 路径和处理
                pathSumQueue.offer(curPathSum + node.right.val);
            }
        }

        // 无满足条件的路径和
        return false;
    }

}
