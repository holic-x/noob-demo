package com.noob.algorithm.dmsxl.leetcode.q637;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 637 二叉树的层平均值
 */
public class Solution1 {

    // 思路：分层统计记录
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集
        List<Double> res = new ArrayList<>();

        // 定义辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化

        // 遍历队列
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            // int curSum = 0; // 初始化当层结果累计
            Double curSum = 0.00; // 初始化当层结果累计
            for (int i = 0; i < curSize; i++) {
                // 取出元素
                TreeNode cur = queue.poll();
                curSum += cur.val;

                // 如果存在左右节点，分别入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层遍历结束，计算平均值
            // res.add( curSum / 1.0 / curSize);
            res.add(curSum / curSize);
        }

        // 返回结果
        return res;
    }
}
