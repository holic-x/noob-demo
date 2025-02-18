package com.noob.algorithm.daily.archive.plan02.day03.p007;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 🟡 102 二叉树的层序遍历 - https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class Solution102_01 {

    /**
     * 层序遍历：从上到下、从左到右 分层遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // root 校验
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集合
        List<List<Integer>> res = new ArrayList<>();
        // 构建队列辅助遍历
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化
        // 队列不为空，继续遍历处理
        while (!queue.isEmpty()) {
            // 计算当层遍历元素个数
            int curSize = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                // 取出队列元素
                TreeNode node = queue.poll();
                temp.add(node.val);
                // 左右子节点处理
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 当层遍历结束，填充结果集
            res.add(temp);
        }
        // 返回结果集合
        return res;
    }
}
