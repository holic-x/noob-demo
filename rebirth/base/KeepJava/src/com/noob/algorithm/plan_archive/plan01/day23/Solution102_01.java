package com.noob.algorithm.plan_archive.plan01.day23;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 102 二叉树的层序遍历 - https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
 */
public class Solution102_01 {

    /**
     * 借助队列辅助遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();  // 定义结果集


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            List<Integer> curList = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                // 取出元素
                TreeNode node = queue.poll();
                curList.add(node.val);
                // 校验左、右子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 将当层遍历元素载入结果集
            res.add(curList);
        }

        // 返回结果
        return res;
    }

}
