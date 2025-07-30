package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 617 合并二叉树 - https://leetcode.cn/problems/merge-two-binary-trees/description/
 */
public class Solution617_02 {

    /**
     * 思路分析：单队列处理思路
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1; // 返回不为null的节点
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 处理节点
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;

            // 需要合并数据(直接覆盖原值)
            node1.val = node1.val + node2.val;

            // 根据子节点的null校验决定合并处理逻辑
            if (left1 != null && left2 != null) {
                // 节点入队
                queue.offer(left1);
                queue.offer(left2);
            }

            if (right1 != null && right2 != null) {
                // 节点入队
                queue.offer(right1);
                queue.offer(right2);
            }

            // 若node1的左节点为空，直接赋值
            if (left1 == null && left2 != null) {
                node1.left = left2;
            }
            // 若node1的右节点为空，直接赋值
            if (right1 == null && right2 != null) {
                node1.right = right2;
            }

        }

        // 返回覆盖处理后的root1
        return root1;
    }

}
