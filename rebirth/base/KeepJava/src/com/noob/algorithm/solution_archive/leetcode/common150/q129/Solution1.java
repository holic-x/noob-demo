package com.noob.algorithm.solution_archive.leetcode.common150.q129;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 求根节点到叶节点数字之和（129）
 */
public class Solution1 {

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        Deque<Integer> valQueue = new LinkedList<>();
        valQueue.offer(root.val);

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                int val = valQueue.poll();

                // 判断是否为叶子节点，如果为叶子节点则将当前路径和进行累加
                if (curNode.left == null && curNode.right == null) {
                    res = res + val;
                }

                // 将节点和对应路径值加入队列
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                    valQueue.offer(val * 10 + curNode.left.val); // 将拼接的数字放入
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                    valQueue.offer(val * 10 + curNode.right.val); // 将拼接的数字放入
                }
            }
        }

        // 返回结果
        return res;
    }

}
