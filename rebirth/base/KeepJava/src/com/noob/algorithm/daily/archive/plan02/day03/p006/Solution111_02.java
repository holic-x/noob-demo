package com.noob.algorithm.daily.archive.plan02.day03.p006;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_02 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * 基于层序遍历思路：转化为层序遍历，从上到下、从左到右寻找第1个根节点
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();
                // 如果节点为叶子节点，则返回当前层数+1
                if (node.left == null && node.right == null) {
                    return depth + 1;
                }
                // 处理左右子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 当层遍历结束
            depth++;
        }

        return -1;
    }

}
