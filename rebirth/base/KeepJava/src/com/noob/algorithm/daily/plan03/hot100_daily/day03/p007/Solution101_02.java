package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 101 对称二叉树 - https://leetcode.cn/problems/symmetric-tree/description/
 */
public class Solution101_02 {

    /**
     * 思路分析：BFS 思路 双队列校验
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 构建双队列比较校验
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(root.left);
        q2.offer(root.right);

        // 遍历节点
        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 取出节点进行比较
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();

            // 比较node1、node2
            if (node1 == null && node2 == null) {
                continue;
            }

            if ((node1 != null && node2 == null) || (node1 == null && node2 != null)) {
                return false;
            }

            if (node1.val != node2.val) {
                return false;
            }

            // 其余情况继续处理左、右节（分组比较）
            q1.offer(node1.left);
            q2.offer(node2.right);
            q1.offer(node1.right);
            q2.offer(node2.left);

        }

        // 校验通过
        return true;
    }

}
