package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 617 合并二叉树 - https://leetcode.cn/problems/merge-two-binary-trees/description/
 */
public class Solution617_01 {

    /**
     * 思路分析：
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1; // 返回不为null的节点
        }

        // 遍历处理
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        Queue<TreeNode> mergeQ = new LinkedList<>();
        q1.offer(root1);
        q2.offer(root2);
        TreeNode newRoot = new TreeNode(root1.val + root2.val);
        mergeQ.offer(newRoot);

        /**
         * 遍历节点：两个队列均不为空的情况下讨论节点
         */
        while (!q1.isEmpty() || !q2.isEmpty()) {
            // 获取节点
            TreeNode n1 = q1.poll();
            TreeNode n2 = q2.poll();
            TreeNode mn = mergeQ.poll();
            // 校验节点: 如果节点均为空则无合并项，如果其中一个为null则选择不为null的那个进行构建，如果均不为null则进行合并

            // 处理子节点
            TreeNode left1 = n1.left;
            TreeNode right1 = n1.right;
            TreeNode left2 = n2.left;
            TreeNode right2 = n2.right;

            /**
             * 节点处理：分类讨论进行挂载。以左子节点为例：
             * - left1!=null && left2!=null: 合并节点进行挂载，并正常进行队列处理
             * - left1或者left2中其中一个为null：选择不为null的进行挂载
             * - left1且left2均为null：不做任何操作
             * 同理 右子节点也是类似的处理思路，最终返回处理后的根节点
             */
            // 左节点处理
            if (left1 != null && left2 != null) {
                TreeNode mergeLeftNode = new TreeNode(left1.val + left2.val);
                mn.left = mergeLeftNode;

                // 节点入队处理
                q1.offer(left1);
                q2.offer(left2);
                mergeQ.offer(mergeLeftNode);
            } else if (left1 == null && left2 != null) {
                mn.left = left2;
            } else if (left1 != null && left2 == null) {
                mn.left = left1;
            }

            // 右节点处理
            if (right1 != null && right2 != null) {
                TreeNode mergeRightNode = new TreeNode(right1.val + right2.val);
                mn.right = mergeRightNode;

                // 节点入队处理
                q1.offer(right1);
                q2.offer(right2);
                mergeQ.offer(mergeRightNode);
            } else if (right1 == null && right2 != null) {
                mn.right = right2;
            } else if (right1 != null && right2 == null) {
                mn.right = right1;
            }

        }

        return newRoot;
    }

}
