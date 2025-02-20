package com.noob.algorithm.solution_archive.dmsxl.leetcode.q404;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 404 左叶子之和
 */
public class Solution1 {

    // 层序遍历：寻找左叶子
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 记录左叶子累加值
        int leftSum = 0;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            // 判断左叶子(遍历当前节点，判断其是否有左叶子：左节点不为空，左节点的左右子节点为空)
            if (cur.left != null && cur.left.left == null && cur.left.right == null) {
                leftSum += cur.left.val; // 左叶子节点值累加
            }

            // 左、右子节点不为空则入队
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        // 返回结果
        return leftSum;
    }
}
