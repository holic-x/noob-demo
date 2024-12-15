package com.noob.algorithm.dmsxl.leetcode.q101;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 101 对称二叉树
 */
public class Solution2 {
    /**
     * 层序遍历思路：初始化将root载入两次
     * 后续遍历每次取出两个节点进行比较，如果验证通过相应将左右节点按照分组（待比较的对称节点为一组）进行先后入队
     */
    public boolean isSymmetric(TreeNode root) {
        // root 为null判断
        if (root == null) {
            return true;
        }

        // 构建辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        // 初始化载入两个root节点（用作同时遍历左右子树进行校验）
        queue.offer(root);
        queue.offer(root);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 每次取出两个节点进行比较：root1、root2
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // 比较两个节点的值是否相同，如果不相同说明不对称，直接返回false
            // 如果两个节点都为null，跳出当轮比较
            if (node1 == null && node2 == null) {
                continue; // 两个节点都为null 无需比较（满足条件，跳过本轮后续的内容）
            }
            // 如果两个节点某一方不存在
            if (node1 == null || node2 == null) {
                return false;
            }
            // 如果两个节点的值存在
            if (node1.val != node2.val) {
                return false;
            }

            /**
             * 如果两个节点的值相同，则继续载入左右节点进行下一轮比较（此处需注意节点的入队顺序，确保每一轮进去的节点按照【待校验的对称节点】进行分组）
             * 即 node1.left 和 node2.right 是一组【待验证的对称节点】、node1.right 和 node2.left 是一组【待验证的对称节点】
             * 只有按照这个顺序入队，才能确保每次两两比较时验证对称的有效性
             */
            // node1.left 和 node2.right 是一组【待验证的对称节点】
            queue.offer(node1.left); // 此处不做子节点的null校验，是为了避免null节点带来的影响，用null占空位
            queue.offer(node2.right);
            // node1.right 和 node2.left 是一组【待验证的对称节点】
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        // 所有验证通过，说明树是对称的
        return true;
    }

}
