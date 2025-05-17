package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.*;

/**
 * 🟡 102 二叉树的层序遍历 - https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class Solution102_01 {

    /**
     * 思路分析：
     * 二叉树的层序遍历（分层存储）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return Collections.emptyList();
        }

        // 定义结果集
        List<List<Integer>> ans = new ArrayList<>();

        // 构建队列辅助存储
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            List<Integer> curList = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();
                curList.add(node.val);
                // 处理左、右子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 当层遍历结束，封装结果集
            ans.add(curList);
        }

        // 返回最终结果集
        return ans;
    }
}
