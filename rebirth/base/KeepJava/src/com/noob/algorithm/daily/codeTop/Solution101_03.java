package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 101 对称二叉树 - https://leetcode.cn/problems/symmetric-tree/description/
 */
public class Solution101_03 {

    // 定义边界值（Node 不可能取到的val）：node.val 属于[-100,100]
    // int INF = 101;
    TreeNode INF = new TreeNode(101); // 定义一个占位节点，表示null节点，辅助队列遍历

    /**
     * 判断节点是否为轴对称，则其左右子树要对称
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 调用bfs方法校验
        return bfs(root.left, root.right);
    }

    /**
     * 递归处理:校验两个子树是否轴对称
     * 相当于同时遍历两棵子树，校验遍历的序列是否完全一致
     * 可以用双队列或者单队列方式
     * ❌❌❌❌❌❌❌❌❌❌❌❌❌
     * 如果此处用INF代替null占位，就会出现问题（null无止境 导致超时限制）
     * 此处还是用null（一开始可能考虑队列加入null怪怪的 想劈叉了）
     */
    private boolean bfs(TreeNode p, TreeNode q) {
        // 定义单队列模式处理
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        // 遍历队列，每次取出两个节点进行校验（此处注意空节点对应位置也要进行默认填充，避免因位置问题导致错误判断）
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // 根据node1、node2 分情况讨论
            // ① node1、node2均为null，符合条件，放行
            if (node1 == INF && node2 == INF) {
                continue; // 对称，放行
            }

            // ② node1、node2其中一个为null
            if ((node1 != INF && node2 == INF) || (node1 == INF && node2 != INF)) {
                return false; // 不对称
            }

            // ③ node1、node2 均不为null
            if (node1 != INF && node2 != INF) {
                // 校验节点值和子树的对称情况（校验值，择选将子树加入队列）
                if (node1.val != node2.val) {
                    return false; // 不对称
                } else {
                    // 值相同，将左右子树相关节点加入队列，等待下一轮比较
                    // node1.left 与 node2.right 一组比较
                    queue.offer(node1.left == null ? INF : node1.left);
                    queue.offer(node2.right == null ? INF : node2.right);
                    // p.right 与 q.left 一组比较
                    queue.offer(node1.right == null ? INF : node1.right);
                    queue.offer(node2.left == null ? INF : node2.left);
                }
            }
        }
        // 校验通过
        return true;
    }

}
