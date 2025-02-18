package com.noob.algorithm.daily.archive.plan02.day04.p010;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟡 513 寻找树左下角的值 - https://leetcode.cn/problems/find-bottom-left-tree-value/description/
 */
public class Solution513_01 {

    /**
     * 思路分析：找出二叉树最底层最左边的节点的值
     * 基于层序遍历，寻找最后一层的第1个节点的值（无法判断当层是否为最后1层则通过覆盖的形式处理）
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int target = -1;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    target = node.val; // 寻找每一层的第1个节点
                }

                // 处理左、右子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        // 返回最终的target
        return target;
    }
}
