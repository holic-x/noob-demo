package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.*;

/**
 * 🟡 102 二叉树的层序遍历 - https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class Solution102_01 {

    /**
     * 思路分析：
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 定义结果集合
        List<List<Integer>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                // 左右子节点处理
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 当层遍历结束，载入结果集
            ans.add(new ArrayList<>(tmp));
        }

        return ans;
    }
}
