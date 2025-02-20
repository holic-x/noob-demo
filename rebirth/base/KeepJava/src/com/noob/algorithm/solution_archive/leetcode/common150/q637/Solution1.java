package com.noob.algorithm.solution_archive.leetcode.common150.q637;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 637 二叉树的层平均值
 */
public class Solution1 {

    /**
     * 基于层序遍历的思路
     */
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 存储每一层的平均值结果
        List<Double> ans = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>(); // 定义辅助队列
        queue.offer(root); // 初始化
        while (!queue.isEmpty()) {
            // 分层
            int size = queue.size();
            // 计算每一层的平均值，并加入结果集
            int curSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curSum += node.val; // 将每一层的元素值进行累加

                // 将node的左右节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 单层遍历结束，计算平均值并加入结果集合
            ans.add((double) curSum / size); // 平均值：和/元素个数
        }
        // 返回结果集
        return ans;
    }
}
