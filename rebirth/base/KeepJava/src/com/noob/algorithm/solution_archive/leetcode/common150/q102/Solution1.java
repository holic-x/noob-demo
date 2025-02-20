package com.noob.algorithm.solution_archive.leetcode.common150.q102;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102 层序遍历
 */
public class Solution1 {
    /**
     * 层序遍历：逐层遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 空节点校验
        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> queue = new LinkedList<>(); // 定义辅助队列
        queue.offer(root); // 初始化

        // 定义结果集
        List<List<Integer>> ans = new ArrayList<>();

        // 遍历队列元素，直至队列为空
        while (!queue.isEmpty()) {

            // 分层遍历
            List<Integer> curList = new ArrayList<>();
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                // 取出节点
                TreeNode node = queue.poll();
                // 将结果加入集合
                curList.add(node.val);

                // 将node的左右节点加入队列
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 单层遍历结束，录入结果集
            ans.add(curList);
        }

        // 返回结果集
        return ans;
    }
}
