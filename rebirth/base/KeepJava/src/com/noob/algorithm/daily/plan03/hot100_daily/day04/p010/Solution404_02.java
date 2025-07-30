package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 404 左叶子之和 - https://leetcode.cn/problems/sum-of-left-leaves/description/
 */
public class Solution404_02 {

    /**
     * 思路分析：给定二叉树的根节点 root ，返回所有左叶子之和
     * - 如果一个节点包括左叶子，则其满足：node.left != null && (node.left.left == null && node.left.right == null)
     */
    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }

        // 遍历思路，可基于BFS方案处理
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 存储左叶子节点和
        int leftLeafSum = 0;

        // 遍历队列
        while (!queue.isEmpty()) {
            // 取出节点，校验其是否存在左叶子节点
            TreeNode node = queue.poll();

            // 校验
            if (node.left != null && node.left.left == null && node.left.right == null) {
                // 存在左叶子节点，累加左叶子节点和
                leftLeafSum += node.left.val;
            }

            // 处理左右节点
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        // 返回结果
        return leftLeafSum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        Solution404_02 s = new Solution404_02();
        s.sumOfLeftLeaves(node);
    }

}
