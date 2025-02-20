package com.noob.algorithm.plan_archive.plan02.hot100.day04.p010;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 617 合并二叉树 - https://leetcode.cn/problems/merge-two-binary-trees/description/
 */
public class Solution617_02 {

    /**
     * 思路分析：合并二叉树
     * BFS（层序遍历）
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        // 构建队列辅助遍历
        Queue<TreeNode> queue1 = new LinkedList<>(); // 定义队列辅助遍历root1
        queue1.offer(root1);
        Queue<TreeNode> queue2 = new LinkedList<>(); // 定义队列辅助遍历root2
        queue2.offer(root2);
        Queue<TreeNode> mergeQueue = new LinkedList<>(); // 定义队列辅助处理合并后的节点
        TreeNode mergeRoot = new TreeNode(root1.val + root2.val);
        mergeQueue.offer(mergeRoot);

        // 当queue1、queue2其中一个不为null，继续合并
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            // 取出节点，一一合并
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            TreeNode mergeNode = mergeQueue.poll(); // 处理节点的左右子节点关系

            // 分左、右节点进行判断
            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;

            // ① 左节点判断
            if (left1 != null && left2 != null) {
                // a.两个子树的左节点均不为null，则需合并构建新的左节点
                TreeNode mergeLeftNode = new TreeNode(left1.val + left2.val);
                // 构建链接关系
                mergeNode.left = mergeLeftNode;
                // 队列处理
                queue1.offer(left1);
                queue2.offer(left2);
                mergeQueue.offer(mergeLeftNode);
            } else if (left1 != null && left2 == null) {
                mergeNode.left = left1; // 选择不为null的节点
            } else if (left1 == null && left2 != null) {
                mergeNode.left = left2; // 选择不为null的节点
            }

            // ② 右节点判断
            if (right1 != null && right2 != null) {
                // a.两个子树的右节点均不为null，则需合并构建新的右节点
                TreeNode mergeRightNode = new TreeNode(right1.val + right2.val);
                // 构建链接关系
                mergeNode.right = mergeRightNode;
                // 队列处理
                queue1.offer(right1);
                queue2.offer(right2);
                mergeQueue.offer(mergeRightNode);
            } else if (right1 != null && right2 == null) {
                mergeNode.right = right1; // 选择不为null的节点
            } else if (right1 == null && right2 != null) {
                mergeNode.right = right2; // 选择不为null的节点
            }
        }
        // 返回合并后的内容
        return mergeRoot;
    }
}
