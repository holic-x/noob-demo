package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 662 二叉树的最大宽度 - https://leetcode.cn/problems/maximum-width-of-binary-tree/description/
 */
public class Solution662_02 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int left = queue.peek().getValue(); // 当前层最左节点的位置
            int right = left; // 当前层最右节点的位置

            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> pair = queue.poll();
                TreeNode node = pair.getKey();
                right = pair.getValue(); // 更新当前层最右位置

                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * right));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * right + 1));
                }
            }
            maxWidth = Math.max(maxWidth, right - left + 1);
        }

        return maxWidth;
    }
}